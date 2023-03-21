package com.duzy.dao;

import com.duzy.model.SshLogModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * ssh日志 Mapper 接口
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-11-20
 */
@Repository
public interface SshLogDao extends BaseMapper<SshLogModel> {

    void insertIntoIpLocation();
}
