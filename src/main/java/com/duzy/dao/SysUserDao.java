package com.duzy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duzy.model.SysUserModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-08-24
 */
@Repository
public interface SysUserDao extends BaseMapper<SysUserModel> {

    Optional<SysUserModel> findByUsername(String username);
}
