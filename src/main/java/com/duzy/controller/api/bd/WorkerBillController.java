package com.duzy.controller.api.bd;

import com.duzy.controller.CustomerController;
import com.duzy.dto.WorkerBillDto;
import com.duzy.dto.query.WorkerBillQueryDto;
import com.duzy.model.WorkerBillModel;
import com.duzy.service.WorkerBillService;
import com.duzy.vo.WorkerBillExportVo;
import com.duzy.vo.WorkerBillVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static com.duzy.common.util.FileUtil.exportList;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-25
 */
@RestController
@RequestMapping("/workerBill")
@Tag(name = "工单")
public class WorkerBillController extends CustomerController<WorkerBillModel, WorkerBillVo, WorkerBillDto, WorkerBillQueryDto> {

    @Autowired
    WorkerBillService service;


    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<WorkerBillExportVo> listByParentName = service.getListByParentName();

        exportList(response, listByParentName);


    }


}
