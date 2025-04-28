package com.duzy.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.duzy.converter.WorkerBillConvertor;
import com.duzy.dao.WorkerBillDao;
import com.duzy.dto.WorkerBillDto;
import com.duzy.model.WorkerBillModel;
import com.duzy.service.WorkerBillService;
import com.duzy.vo.WorkerBillExportVo;
import com.duzy.vo.WorkerBillVo;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-25
 */
@Service
public class WorkerBillServiceImpl extends CustomerServiceImpl<WorkerBillModel, WorkerBillVo, WorkerBillDto, WorkerBillDao> implements WorkerBillService {
    public static final int MAX_LENGTH = 1700;
    public static final String BATCH_SPARE = "@";
    public static final String SPLITE_UNDER_LINE = "_";
    public static final String SPLITE_UP_LINE = "|";


    @Autowired
    WorkerBillDao workerBillDao;
    @Autowired
    WorkerBillConvertor convertor;

    /**
     * @return
     */
    @Override
    public List<WorkerBillExportVo> getListByParentName() {
        HashMap<String, String> resultMap = Maps.newHashMap();
        List<WorkerBillExportVo> resultList = new ArrayList<>();

        Multimap<String, String> multimap = getMultimap();
        Set<Map.Entry<String, Collection<String>>> entries = multimap.asMap().entrySet();
        entries.forEach(entry -> {
            AtomicInteger count = new AtomicInteger(0);
            String keyPrefix = entry.getKey() + BATCH_SPARE;
            String currentKey = keyPrefix + count.get();
            String nextKey = keyPrefix + count.incrementAndGet();

            for (String value : entry.getValue()) {
                int totalLength = resultMap.containsKey(currentKey) ? resultMap.get(currentKey).length() : 0;
                if (totalLength == 0) {
                    resultMap.put(currentKey, value);
                } else if (totalLength + value.length() <= MAX_LENGTH) {
                    resultMap.put(currentKey, resultMap.get(currentKey) + SPLITE_UP_LINE + value);
                } else {
                    resultMap.put(nextKey, value);
                    currentKey = nextKey;
                    nextKey = currentKey.split(BATCH_SPARE)[0] + BATCH_SPARE + count.incrementAndGet();
                }
            }
        });
        resultMap.forEach((k, v) -> {
            WorkerBillExportVo workerBillExportVo = new WorkerBillExportVo();
            String[] s = k.split(SPLITE_UNDER_LINE);
            int length = s.length;
            workerBillExportVo.setWorkTypeParentName(length > 0 ? s[0] : "");
            workerBillExportVo.setWorkTypeSecondName(length > 1 ? s[1] : "");
            if (length > 2) {
                String contentContainsAt = s[2];
                String content = contentContainsAt.contains(BATCH_SPARE) ? contentContainsAt.substring(0, contentContainsAt.lastIndexOf(BATCH_SPARE)) : contentContainsAt;
                workerBillExportVo.setWorkTypeFullName(content);
            } else {
                workerBillExportVo.setWorkTypeFullName("");
            }
            workerBillExportVo.setContent(v);
            resultList.add(workerBillExportVo);
        });
        return resultList;
    }

    @Cacheable(cacheManager = "redisCacheManager", key = "#root.methodName")
    @Override
    public Multimap<String, String> getMultimap() {
        Multimap<String, String> multimap = ArrayListMultimap.create();
        //查询出所有分类
        LambdaQueryWrapper<WorkerBillModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .select(WorkerBillModel::getWorkTypeParentName, WorkerBillModel::getWorkTypeSecondName, WorkerBillModel::getWorkTypeFullName)
                .groupBy(WorkerBillModel::getWorkTypeParentName)
                .groupBy(WorkerBillModel::getWorkTypeSecondName)
                .groupBy(WorkerBillModel::getWorkTypeFullName);
        List<WorkerBillModel> workerBillGroupByResult = workerBillDao.selectList(queryWrapper);

        workerBillGroupByResult.forEach(groupRow -> {
            LambdaQueryWrapper<WorkerBillModel> queryWrapperInGroup = new LambdaQueryWrapper<>();
            String workTypeParentName = groupRow.getWorkTypeParentName();
            String workTypeSecondName = groupRow.getWorkTypeSecondName();
            String workTypeFullName = groupRow.getWorkTypeFullName();
            queryWrapperInGroup
                    .select(WorkerBillModel::getWorkTypeParentName, WorkerBillModel::getWorkTypeSecondName, WorkerBillModel::getWorkTypeFullName, WorkerBillModel::getContent)
            ;
            if (workTypeParentName == null) {
                queryWrapperInGroup.isNull(WorkerBillModel::getWorkTypeParentName);
            } else {
                queryWrapperInGroup.eq(WorkerBillModel::getWorkTypeParentName, workTypeParentName);
            }

            if (workTypeSecondName == null) {
                queryWrapperInGroup.isNull(WorkerBillModel::getWorkTypeSecondName);
            } else {
                queryWrapperInGroup.eq(WorkerBillModel::getWorkTypeSecondName, workTypeSecondName);
            }

            if (workTypeFullName == null) {
                queryWrapperInGroup.isNull(WorkerBillModel::getWorkTypeFullName);
            } else {
                queryWrapperInGroup.eq(WorkerBillModel::getWorkTypeFullName, workTypeFullName);
            }


            List<WorkerBillModel> workerBillModels = workerBillDao.selectList(queryWrapperInGroup);


            workerBillModels.forEach(model -> {
                String content = model.getContent();
                if (StrUtil.isNotBlank(content)) {
                    String key = StrUtil.format("{}_{}_{}", workTypeParentName, workTypeSecondName, workTypeFullName);
                    multimap.put(key, content);
                }
            });
        });


        return multimap;
    }
}
