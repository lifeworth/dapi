package com.duzy.service.impl;

import com.duzy.model.BdCarChangeRecordModel;
import com.duzy.dao.BdCarChangeRecordDao;
import com.duzy.service.BdCarChangeRecordService;
import org.springframework.stereotype.Service;

import com.duzy.dto.BdCarChangeRecordDto;
import com.duzy.vo.BdCarChangeRecordVo;
/**
 * <p>
 * 车位变更记录 服务实现类
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */
@Service
public class BdCarChangeRecordServiceImpl extends CustomerServiceImpl<BdCarChangeRecordModel,BdCarChangeRecordVo,BdCarChangeRecordDto,BdCarChangeRecordDao> implements BdCarChangeRecordService {

}
