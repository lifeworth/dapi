package com.duzy.converter;

import com.duzy.dto.SysRoleDto;
import com.duzy.model.SysRoleModel;
import com.duzy.vo.SysRoleVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

/**
 * @author zhiyuandu
 * @since 2023/3/20 11:33
 * @description
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface SysRoleConverter extends CustomerConverter<SysRoleDto, SysRoleModel, SysRoleVo> {
}
