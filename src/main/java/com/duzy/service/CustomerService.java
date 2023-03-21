package com.duzy.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.duzy.converter.CustomerConverter;
import com.duzy.dto.BaseQueryDTO;
import com.duzy.dto.CustomerDto;
import com.duzy.model.CustomerModel;
import com.duzy.vo.CustomerVo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.duzy.common.Constant.DEFAULT_PAGE_INDEX;
import static com.duzy.common.Constant.DEFAULT_PAGE_SIZE;

/**
 * @author zhiyuandu
 * @since 2023/3/18 17:52
 * @description
 */
public interface CustomerService<T extends CustomerModel, V extends CustomerVo, D extends CustomerDto> extends IService<T> {

    /**
     * 获取类型转换器
     * @return 类型转换器
     */
    CustomerConverter<D, T, V> getBaseConverter();

    /**
     * 根据id获取vo
     * @param id id
     * @return vo
     */
    default V customerGetById(Integer id) {
        CustomerConverter<D, T, V> baseConverter = getBaseConverter();
        BaseMapper<T> baseMapper = getBaseMapper();
        T t = baseMapper.selectById(id);
        return baseConverter.model2vo(t);
    }

    /**
     * 创建
     * @param d dto
     */
    default void customerCreate(D d) {
        CustomerConverter<D, T, V> baseConverter = getBaseConverter();
        T t = baseConverter.dto2Model(d);
        BaseMapper<T> baseMapper = getBaseMapper();
        baseMapper.insert(t);
    }

    /**
     *更新
     * @param d dto
     */
    default void customerUpdate(D d) {
        CustomerConverter<D, T, V> baseConverter = getBaseConverter();
        T t = baseConverter.dto2Model(d);
        BaseMapper<T> baseMapper = getBaseMapper();
        baseMapper.updateById(t);
    }

    /**
     * 根据id删除
     * @param id id
     */
    default void customerDelete(Integer id) {
        BaseMapper<T> baseMapper = getBaseMapper();
        baseMapper.deleteById(id);
    }

    /**
     * 查询list
     *
     * @param q 查询dto .默认循环dto中不为空的属性，如果有值则使用eq方法
     * @return voList
     */
    default List<V> customerList(BaseQueryDTO q) {
        BaseMapper<T> baseMapper = getBaseMapper();
        Map<String, Object> map = BeanUtil.beanToMap(q, true, true);
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>().allEq(CollUtil.isNotEmpty(map), map, true);
        List<T> modelList = baseMapper.selectList(queryWrapper);
        CustomerConverter<D, T, V> baseConverter = getBaseConverter();
        return baseConverter.model2voList(modelList);
    }

    /**
     * 查询page
     *
     * @param q 查询dto
     * @return voPage
     */
    default Page<V> customerPage(BaseQueryDTO q) {
        BaseMapper<T> baseMapper = getBaseMapper();
        Map<String, Object> map = BeanUtil.beanToMap(q, true, true);
        LocalDateTime createTimeStart = q.getCreateTimeStart();
        LocalDateTime createTimeEnd = q.getCreateTimeEnd();

        LocalDateTime updateTimeStart = q.getUpdateTimeStart();
        LocalDateTime updateTimeEnd = q.getUpdateTimeEnd();
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>()
                .allEq(CollUtil.isNotEmpty(map), map, true)
                .between(Objects.nonNull(createTimeStart) && Objects.nonNull(createTimeEnd), "created_time", createTimeStart, createTimeEnd)
                .between(Objects.nonNull(updateTimeStart) && Objects.nonNull(updateTimeEnd), "updated_time", updateTimeStart, updateTimeEnd);

        Integer pageIndex = q.getCurrent();
        Integer pageSize = q.getSize();
        Page<T> page = new Page<>(Objects.isNull(pageIndex) ? DEFAULT_PAGE_INDEX : pageIndex, Objects.isNull(pageSize) ? DEFAULT_PAGE_SIZE : pageSize);
        Page<T> pageResult = baseMapper.selectPage(page, queryWrapper);
        CustomerConverter<D, T, V> baseConverter = getBaseConverter();
        return baseConverter.modelPage2ListPage(pageResult);
    }
}
