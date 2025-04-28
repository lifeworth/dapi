package com.duzy.service;

import com.duzy.dto.WorkerBillDto;
import com.duzy.model.WorkerBillModel;
import com.duzy.vo.WorkerBillExportVo;
import com.duzy.vo.WorkerBillVo;
import com.google.common.collect.Multimap;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-25
 */
public interface WorkerBillService extends CustomerService<WorkerBillModel, WorkerBillVo, WorkerBillDto> {

    List<WorkerBillExportVo> getListByParentName();

    Multimap<String, String> getMultimap();
}
