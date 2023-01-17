package com.duzy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duzy.converter.IpLocationConverter;
import com.duzy.dao.IpLocationDao;
import com.duzy.dto.IpLocationDTO;
import com.duzy.dto.IpQueryDTO;
import com.duzy.fetures.kafka.KafkaProducer;
import com.duzy.fetures.kafka.KafkaSampleMessage;
import com.duzy.model.IpLocationModel;
import com.duzy.service.IpLocationService;
import com.duzy.vo.IpVo;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.duzy.common.Constant.*;

/**
 * <p>
 * ip地理位置信息 服务实现类
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-11-14
 */
@Service
@Slf4j
public class IpLocationServiceImpl extends ServiceImpl<IpLocationDao, IpLocationModel> implements IpLocationService {
    @Value("${post.ip.url}")
    String postUrl;
    @Autowired
    IpLocationConverter ipLocationConverter;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Value("${ip-api-com.lock}")
    private String key;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public List<IpLocationModel> parseFromApi() {
        List<IpLocationModel> models = list(new LambdaQueryWrapper<IpLocationModel>().isNull(IpLocationModel::getStatus));
        List<String> ips = models.stream().map(IpLocationModel::getIp).collect(Collectors.toList());
        List<IpLocationModel> result = new ArrayList<>();
        RRateLimiter rateLimiter = redissonClient.getRateLimiter("lock");
        // 设置速率，5秒中产生1个令牌
        rateLimiter.trySetRate(RateType.OVERALL, 1, 4, RateIntervalUnit.SECONDS);

        CollUtil.split(ips, MAX_BATCH_SIZE).forEach(list -> {
            rateLimiter.acquire();
            JSONArray body = requestApi(list);
            assert body != null;
            List<IpLocationDTO> dtos = body.toList(IpLocationDTO.class);
            for (int j = 0; j < dtos.size(); j++) {
                IpLocationDTO dto = dtos.get(j);
                toDb(dto, models);
                toKafka(dto, j);
            }
        });
        return result;
    }

    @Override
    public Page<IpVo> list(IpQueryDTO queryDTO) {
        Page<IpVo> result = new Page<>();
        Integer pageIndex = queryDTO.getPageIndex();
        Integer pageSize = queryDTO.getPageSize();
        int index = Objects.isNull(pageIndex) ? DEFAULT_PAGE_INDEX : pageIndex;
        int size = Objects.isNull(pageSize) ? DEFAULT_PAGE_SIZE : pageSize;
        LambdaQueryWrapper<IpLocationModel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(queryDTO.getId()), IpLocationModel::getId, queryDTO.getId());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getAs()), IpLocationModel::getAs, queryDTO.getAs());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getAsname()), IpLocationModel::getAsname, queryDTO.getAsname());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getCity()), IpLocationModel::getCity, queryDTO.getCity());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getContinent()), IpLocationModel::getContinent, queryDTO.getContinent());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getContinentCode()), IpLocationModel::getContinentCode, queryDTO.getContinentCode());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getCountry()), IpLocationModel::getCountry, queryDTO.getCountry());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getCountryCode()), IpLocationModel::getCountryCode, queryDTO.getCountryCode());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getCurrency()), IpLocationModel::getCurrency, queryDTO.getCurrency());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getDistrict()), IpLocationModel::getDistrict, queryDTO.getDistrict());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getHosting()), IpLocationModel::getHosting, queryDTO.getHosting());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getIp()), IpLocationModel::getIp, queryDTO.getIp());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getIsp()), IpLocationModel::getIsp, queryDTO.getIsp());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getLat()), IpLocationModel::getLat, queryDTO.getLat());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getLon()), IpLocationModel::getLon, queryDTO.getLon());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getMessage()), IpLocationModel::getMessage, queryDTO.getMessage());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getMobile()), IpLocationModel::getMobile, queryDTO.getMobile());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getOffset()), IpLocationModel::getOffset, queryDTO.getOffset());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getOrg()), IpLocationModel::getOrg, queryDTO.getOrg());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getProxy()), IpLocationModel::getProxy, queryDTO.getProxy());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getQuery()), IpLocationModel::getQuery, queryDTO.getQuery());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getRegion()), IpLocationModel::getRegion, queryDTO.getRegion());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getRegionName()), IpLocationModel::getRegionName, queryDTO.getRegionName());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getStatus()), IpLocationModel::getStatus, queryDTO.getStatus());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getTimezone()), IpLocationModel::getTimezone, queryDTO.getTimezone());
        wrapper.like(!Strings.isNullOrEmpty(queryDTO.getZip()), IpLocationModel::getZip, queryDTO.getZip());

        wrapper.between(Objects.nonNull(queryDTO.getCreateTimeStart()) && Objects.nonNull(queryDTO.getCreateTimeEnd()), IpLocationModel::getCreatedTime, queryDTO.getCreateTimeStart(), queryDTO.getCreateTimeEnd());
        wrapper.between(Objects.nonNull(queryDTO.getCreateTimeStart()) && Objects.nonNull(queryDTO.getCreateTimeEnd()), IpLocationModel::getUpdatedTime, queryDTO.getUpdateTimeStart(), queryDTO.getUpdateTimeEnd());

        Page<IpLocationModel> page = page(Page.of(index, size), wrapper);

        List<IpLocationModel> records = page.getRecords();
        List<IpVo> vos = ipLocationConverter.model2Vos(records);
        BeanUtil.copyProperties(page, result, "records");
        result.setRecords(vos);
        return result;
    }

    @Nullable
    private JSONArray requestApi(List<String> list) {
        ResponseEntity<JSONArray> response = restTemplate.exchange(postUrl, HttpMethod.POST, new HttpEntity<>(list), JSONArray.class);
        HttpStatusCode statusCode = response.getStatusCode();
        if (statusCode == HttpStatus.TOO_MANY_REQUESTS) {
            log.error("TOO_MANY_REQUESTS");
            throw new RuntimeException("请求API失败! TOO_MANY_REQUESTS");
        }
        if (statusCode == HttpStatus.UNPROCESSABLE_ENTITY) {
            log.error("单次请求数量超出限制");
        }
        List<String> rateLimit = response.getHeaders().get("X-Rl");
        List<String> ttl = response.getHeaders().get("X-Ttl");
        JSONArray body = response.getBody();
        log.info("rateLimit:{},ttl{}.body:{}.response:{}", rateLimit, ttl, body, response);
        return body;
    }

    private void toDb(IpLocationDTO dto, List<IpLocationModel> models) {
        models.stream().filter(model -> model.getIp().equals(dto.getQuery())).findFirst().ifPresent(model -> {
            dto.setId(model.getId());
            IpLocationModel ipLocationModel = ipLocationConverter.dto2Model(dto);
            log.info("更新id:{}", dto.getId());
            updateById(ipLocationModel);
        });
    }

    private void toKafka(IpLocationDTO ipLocationDto, int i) {
        try {
            KafkaSampleMessage kafkaSampleMessage = new KafkaSampleMessage();
            kafkaSampleMessage.setId(i);
            kafkaSampleMessage.setMessage(JSONUtil.toJsonStr(ipLocationDto));
            kafkaProducer.send(kafkaSampleMessage);
        } catch (Exception e) {
            log.error("发送到kafka失败:{}.", e.getMessage());
        }
    }
}
