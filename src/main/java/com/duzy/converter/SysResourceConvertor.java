package com.duzy.converter;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import com.duzy.model.SysResourceModel;
import com.duzy.dto.SysResourceDto;
import com.duzy.vo.SysResourceVo;
/**
 * <p>
 * 权限/资源 转换器
 * </p>
 *
 * @author zhiyuandu
 * @since 2023-03-21
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface SysResourceConvertor extends CustomerConverter<SysResourceDto,SysResourceModel,SysResourceVo> {

}
