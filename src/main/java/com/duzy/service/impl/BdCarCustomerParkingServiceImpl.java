package com.duzy.service.impl;

import com.duzy.model.BdCarCustomerParkingModel;
import com.duzy.dao.BdCarCustomerParkingDao;
import com.duzy.service.BdCarCustomerParkingService;
import org.springframework.stereotype.Service;

import com.duzy.dto.BdCarCustomerParkingDto;
import com.duzy.vo.BdCarCustomerParkingVo;
/**
 * <p>
 * 车辆人员车位关系 服务实现类
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */
@Service
public class BdCarCustomerParkingServiceImpl extends CustomerServiceImpl<BdCarCustomerParkingModel,BdCarCustomerParkingVo,BdCarCustomerParkingDto,BdCarCustomerParkingDao> implements BdCarCustomerParkingService {

}
