package com.duzy.converter;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import com.duzy.model.BdCarChangeRecordModel;
import com.duzy.dto.BdCarChangeRecordDto;
import com.duzy.vo.BdCarChangeRecordVo;
/**
 * <p>
 * 车位变更记录 转换器
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface BdCarChangeRecordConvertor extends CustomerConverter<BdCarChangeRecordDto,BdCarChangeRecordModel,BdCarChangeRecordVo> {

}
