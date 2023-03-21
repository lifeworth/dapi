package com.duzy.service.impl;

import com.duzy.dao.SysRoleDao;
import com.duzy.dto.SysRoleDto;
import com.duzy.model.SysRoleModel;
import com.duzy.service.SysRoleService;
import com.duzy.vo.SysRoleVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author zhiyuandu
 * @since 2023-03-18
 */
@Service
public class SysRoleServiceImpl extends CustomerServiceImpl<SysRoleModel, SysRoleVo, SysRoleDto, SysRoleDao> implements SysRoleService {

}
