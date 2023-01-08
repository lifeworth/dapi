package com.duzy.converter;

import com.duzy.dto.IpLocationDTO;
import com.duzy.model.IpLocationModel;
import com.duzy.vo.IpVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface IpLocationConverter {


    @Mapping(target = "ip", source = "query")
    IpLocationModel dto2Model(IpLocationDTO dto);

    List<IpLocationModel> dto2ModelList(List<IpLocationDTO> dtoList);

    List<IpVo> model2Vos(List<IpLocationModel> records);

    IpVo model2Vos(IpLocationModel model);
}
