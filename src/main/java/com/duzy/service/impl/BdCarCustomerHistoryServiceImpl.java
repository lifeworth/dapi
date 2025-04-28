package com.duzy.service.impl;

import com.duzy.model.BdCarCustomerHistoryModel;
import com.duzy.dao.BdCarCustomerHistoryDao;
import com.duzy.service.BdCarCustomerHistoryService;
import org.springframework.stereotype.Service;

import com.duzy.dto.BdCarCustomerHistoryDto;
import com.duzy.vo.BdCarCustomerHistoryVo;
/**
 * <p>
 * 车位历史客户 服务实现类
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */
@Service
public class BdCarCustomerHistoryServiceImpl extends CustomerServiceImpl<BdCarCustomerHistoryModel,BdCarCustomerHistoryVo,BdCarCustomerHistoryDto,BdCarCustomerHistoryDao> implements BdCarCustomerHistoryService {

}
