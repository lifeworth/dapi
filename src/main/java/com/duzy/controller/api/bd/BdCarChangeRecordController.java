package com.duzy.controller.api.bd;

import com.duzy.controller.CustomerController;
import com.duzy.dto.BdCarChangeRecordDto;
import com.duzy.model.BdCarChangeRecordModel;
import com.duzy.dto.query.BdCarChangeRecordQueryDto;
import com.duzy.vo.BdCarChangeRecordVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 车位变更记录 前端控制器
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */
@RestController
@RequestMapping("/bdCarChangeRecordModel")
@Tag(name = "车位变更记录")
public class BdCarChangeRecordController extends CustomerController<BdCarChangeRecordModel,BdCarChangeRecordVo,BdCarChangeRecordDto,BdCarChangeRecordQueryDto> {
}
