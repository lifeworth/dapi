package com.duzy.controller.api.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duzy.model.SysResourceModel;
import com.duzy.dto.SysResourceDto;
import com.duzy.vo.SysResourceVo;
import com.duzy.dto.query.SysResourceQueryDto;

/**
 * <p>
 * 权限/资源 前端控制器
 * </p>
 *
 * @author zhiyuandu
 * @since 2023-03-21
 */
@RestController
@RequestMapping("/sysResourceModel")
public class SysResourceController extends CustomerController<SysResourceModel,SysResourceVo,SysResourceDto,SysResourceQueryDto> {
}
