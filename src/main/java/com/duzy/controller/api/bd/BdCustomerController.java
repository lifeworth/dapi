package com.duzy.controller.api.bd;

import com.duzy.controller.CustomerController;
import com.duzy.dto.BdCustomerDto;
import com.duzy.model.BdCustomerModel;
import com.duzy.dto.query.BdCustomerQueryDto;
import com.duzy.vo.BdCustomerVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 业户表，业主租户 前端控制器
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */
@RestController
@RequestMapping("/bdCustomerModel")
@Tag(name = "业户表，业主租户")
public class BdCustomerController extends CustomerController<BdCustomerModel,BdCustomerVo,BdCustomerDto,BdCustomerQueryDto> {
}
