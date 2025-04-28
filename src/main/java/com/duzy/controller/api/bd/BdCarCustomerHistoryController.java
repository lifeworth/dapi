package com.duzy.controller.api.bd;

import com.duzy.controller.CustomerController;
import com.duzy.dto.BdCarCustomerHistoryDto;
import com.duzy.model.BdCarCustomerHistoryModel;
import com.duzy.dto.query.BdCarCustomerHistoryQueryDto;
import com.duzy.vo.BdCarCustomerHistoryVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 车位历史客户 前端控制器
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */
@RestController
@RequestMapping("/bdCarCustomerHistoryModel")
@Tag(name = "车位历史客户")
public class BdCarCustomerHistoryController extends CustomerController<BdCarCustomerHistoryModel,BdCarCustomerHistoryVo,BdCarCustomerHistoryDto,BdCarCustomerHistoryQueryDto> {
}
