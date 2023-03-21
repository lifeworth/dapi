package com.duzy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duzy.model.IpLocationModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * ip地理位置信息 Mapper 接口
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-11-14
 */
@Repository
public interface IpLocationDao extends BaseMapper<IpLocationModel> {

}
