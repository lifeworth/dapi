package com.duzy.controller.api.user;

import com.duzy.dto.SysResourceDto;
import com.duzy.dto.query.SysResourceQueryDto;
import com.duzy.model.SysResourceModel;
import com.duzy.vo.SysResourceVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Tag(name = "权限/资源")
public class SysResourceController extends CustomerController<SysResourceModel, SysResourceVo, SysResourceDto, SysResourceQueryDto> {
}
