package com.duzy.converter;

import com.duzy.dto.UserDTO;
import com.duzy.model.UserModel;
import com.duzy.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface UserConverter {

    UserModel dto2Model(UserDTO userDTO);

    UserVO model2Vo(UserModel model);

    List<UserVO> model2VoList(List<UserModel> modelList);
}
