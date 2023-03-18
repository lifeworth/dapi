package com.duzy.converter;

import com.duzy.dto.UserDto;
import com.duzy.model.SysUserModel;
import com.duzy.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface UserConverter {

    SysUserModel dto2Model(UserDto userQueryDto);

    @Mapping(target = "password", expression = "java(cn.hutool.core.util.DesensitizedUtil.password(model.getPassword()))")
    @Mapping(target = "phone", expression = "java(cn.hutool.core.util.DesensitizedUtil.mobilePhone(model.getPhone()))")
    @Mapping(target = "email", expression = "java(cn.hutool.core.util.DesensitizedUtil.email(model.getEmail()))")
    UserVO model2Vo(SysUserModel model);

    List<UserVO> model2VoList(List<SysUserModel> modelList);
}
