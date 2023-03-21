package com.duzy.controller.api.user;

import com.duzy.dto.SysRoleDto;
import com.duzy.dto.query.SysRoleQueryDto;
import com.duzy.model.SysRoleModel;
import com.duzy.vo.SysRoleVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author zhiyuandu
 * @since 2023-03-18
 */
@RestController
@RequestMapping("/sysRoleModel")
public class SysRoleController extends CustomerController<SysRoleModel, SysRoleVo, SysRoleDto, SysRoleQueryDto> {

}
