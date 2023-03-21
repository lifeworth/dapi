package com.duzy.controller.api.user;

import com.duzy.dto.SysRoleDto;
import com.duzy.dto.query.SysRoleQueryDto;
import com.duzy.model.SysRoleModel;
import com.duzy.vo.SysRoleVo;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/manage/role")
@Tag(name = "角色")
public class SysRoleController extends CustomerController<SysRoleModel, SysRoleVo, SysRoleDto, SysRoleQueryDto> {

}
