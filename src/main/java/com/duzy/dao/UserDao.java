package com.duzy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duzy.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-08-24
 */
@Mapper
public interface UserDao extends BaseMapper<UserModel> {

}
