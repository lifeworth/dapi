package com.duzy.dao;

import com.duzy.model.SshLogModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * ssh日志 Mapper 接口
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-11-20
 */
@Mapper
public interface SshLogDao extends BaseMapper<SshLogModel> {

}
