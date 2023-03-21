package com.duzy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.duzy.dto.UserDto;
import com.duzy.dto.query.UserQueryDto;
import com.duzy.model.SysUserModel;
import com.duzy.vo.TokenVO;
import com.duzy.vo.UserVO;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-08-24
 */
public interface UserService extends IService<SysUserModel> {

    void save(UserDto dto);

    void update(UserDto dto);

    UserVO getById(Integer id);

    List<UserVO> list(UserQueryDto dto);

    Page<UserVO> page(UserQueryDto dto);

    TokenVO login(UserDto userDto);

    UserVO currentUser();

    void outLogin();
}
