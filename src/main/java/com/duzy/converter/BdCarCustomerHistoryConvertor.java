package com.duzy.converter;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import com.duzy.model.BdCarCustomerHistoryModel;
import com.duzy.dto.BdCarCustomerHistoryDto;
import com.duzy.vo.BdCarCustomerHistoryVo;
/**
 * <p>
 * 车位历史客户 转换器
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface BdCarCustomerHistoryConvertor extends CustomerConverter<BdCarCustomerHistoryDto,BdCarCustomerHistoryModel,BdCarCustomerHistoryVo> {

}
