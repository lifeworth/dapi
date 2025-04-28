package com.duzy.controller.api.bd;

import com.duzy.controller.CustomerController;
import com.duzy.dto.BdCarCustomerParkingDto;
import com.duzy.model.BdCarCustomerParkingModel;
import com.duzy.dto.query.BdCarCustomerParkingQueryDto;
import com.duzy.vo.BdCarCustomerParkingVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 车辆人员车位关系 前端控制器
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */
@RestController
@RequestMapping("/bdCarCustomerParkingModel")
@Tag(name = "车辆人员车位关系")
public class BdCarCustomerParkingController extends CustomerController<BdCarCustomerParkingModel,BdCarCustomerParkingVo,BdCarCustomerParkingDto,BdCarCustomerParkingQueryDto> {
}
