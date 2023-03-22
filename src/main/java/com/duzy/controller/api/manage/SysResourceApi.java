package com.duzy.controller.api.manage;

import org.springframework.web.bind.annotation.RequestMapping;
import com.duzy.controller.CustomerController;
import org.springframework.web.bind.annotation.RestController;

import com.duzy.model.SysResourceModel;
import com.duzy.dto.SysResourceDto;
import com.duzy.vo.SysResourceVo;
import com.duzy.dto.query.SysResourceQueryDto;
import io.swagger.v3.oas.annotations.tags.Tag;
/**
 * <p>
 * 权限/资源 前端控制器
 * </p>
 *
 * @author zhiyuandu
 * @since 2023-03-21
 */
@RestController
@RequestMapping("/api/manage/resource")
@Tag(name = "权限-资源")
public class SysResourceApi extends CustomerController<SysResourceModel,SysResourceVo,SysResourceDto,SysResourceQueryDto> {
}
