package com.duzy.converter;

import com.duzy.dto.IpLocationDto;
import com.duzy.model.IpLocationModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface IpLocationConverter {


    @Mapping(target = "ip",source = "query")
    IpLocationModel dto2Model(IpLocationDto dto);

    List<IpLocationModel> dto2ModelList(List<IpLocationDto> dtoList);
}
