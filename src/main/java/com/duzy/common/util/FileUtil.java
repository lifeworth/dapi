package com.duzy.common.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.duzy.vo.WorkerBillExportVo;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class FileUtil {

    public static void exportList(HttpServletResponse response, List<WorkerBillExportVo> listByParentName) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(listByParentName, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=test.xlsx");
        ServletOutputStream out = response.getOutputStream();

        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }
}
