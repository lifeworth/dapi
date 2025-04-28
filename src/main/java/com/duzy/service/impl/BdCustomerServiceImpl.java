package com.duzy.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.dto.BaseQueryDTO;
import com.duzy.model.BdCustomerModel;
import com.duzy.dao.BdCustomerDao;
import com.duzy.service.BdCustomerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.duzy.dto.BdCustomerDto;
import com.duzy.vo.BdCustomerVo;
/**
 * <p>
 * 业户表，业主租户 服务实现类
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */
@Service
public class BdCustomerServiceImpl extends CustomerServiceImpl<BdCustomerModel,BdCustomerVo,BdCustomerDto,BdCustomerDao> implements BdCustomerService {

    /**
     * 根据id获取vo
     *
     * @param id id
     * @return vo
     */
    @Override
    @Cacheable(key = "#root.methodName_#id",cacheManager = "redisCacheManager")
    public BdCustomerVo customerGetById(Integer id) {
        return super.customerGetById(id);
    }

    /**
     * 查询page
     *
     * @param q 查询dto
     * @return voPage
     */
    @Override
    @Cacheable(key = "#q.current + '_' + #q.size + '_' + #q.name",
            cacheManager = "redisCacheManager",
            value = "customerPage")
    public Page<BdCustomerVo> customerPage(BaseQueryDTO q) {
        return super.customerPage(q);
    }
}
