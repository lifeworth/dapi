package com.duzy.converter;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import com.duzy.model.WorkerBillModel;
import com.duzy.dto.WorkerBillDto;
import com.duzy.vo.WorkerBillVo;
/**
 * <p>
 *  转换器
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-25
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface WorkerBillConvertor extends CustomerConverter<WorkerBillDto,WorkerBillModel,WorkerBillVo> {

}
