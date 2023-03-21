package com.duzy.dao;

import com.duzy.model.NginxLogModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * nginx日志 Mapper 接口
 * </p>
 *
 * @author zhiyuandu
 * @since 2023-01-08
 */
@Repository
public interface NginxLogDao extends BaseMapper<NginxLogModel> {

    void insertIntoIpLocation();
}
