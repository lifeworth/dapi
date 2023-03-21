package com.duzy.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duzy.converter.CustomerConverter;
import com.duzy.dto.CustomerDto;
import com.duzy.model.CustomerModel;
import com.duzy.service.CustomerService;
import com.duzy.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhiyuandu
 * @since 2023/3/20 14:04
 * @description
 */
public class CustomerServiceImpl<T extends CustomerModel, V extends CustomerVo, D extends CustomerDto, M extends BaseMapper<T>>
        extends ServiceImpl<M, T>
        implements CustomerService<T, V, D> {
    @Autowired
    protected CustomerConverter<D, T, V> customerConverter;

    @Override
    public CustomerConverter<D, T, V> getBaseConverter() {
        return customerConverter;
    }
}
