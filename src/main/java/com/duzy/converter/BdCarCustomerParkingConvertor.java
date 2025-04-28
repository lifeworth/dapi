package com.duzy.converter;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import com.duzy.model.BdCarCustomerParkingModel;
import com.duzy.dto.BdCarCustomerParkingDto;
import com.duzy.vo.BdCarCustomerParkingVo;
/**
 * <p>
 * 车辆人员车位关系 转换器
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface BdCarCustomerParkingConvertor extends CustomerConverter<BdCarCustomerParkingDto,BdCarCustomerParkingModel,BdCarCustomerParkingVo> {

}
