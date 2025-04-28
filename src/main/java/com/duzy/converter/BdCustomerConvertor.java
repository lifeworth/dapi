package com.duzy.converter;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import com.duzy.model.BdCustomerModel;
import com.duzy.dto.BdCustomerDto;
import com.duzy.vo.BdCustomerVo;
/**
 * <p>
 * 业户表，业主租户 转换器
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface BdCustomerConvertor extends CustomerConverter<BdCustomerDto,BdCustomerModel,BdCustomerVo> {

}
