package com.duzy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.dto.UserDTO;
import com.baomidou.mybatisplus.extension.service.IService;
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

    void save(UserDTO dto);

    void update(UserDTO dto);

    UserVO getById(Integer id);

    List<UserVO> list(UserDTO dto);

    Page<UserVO>  page(UserDTO dto);

    TokenVO login(UserDTO userDTO);
}
