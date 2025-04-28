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

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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

//        exportListFile(response, listByParentName);
        FileWriter writer = new FileWriter("e:/testWrite.csv");

        listByParentName
                .stream()
                .map(item -> item.getWorkTypeParentName() + " " + item.getWorkTypeSecondName() + " " + item.getWorkTypeFullName() + " " + item.getContent())
                .forEach(item -> {
                    try {
                        writer.append(item);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        writer.close();
    }


}
