package com.duzy.controller.api.ip;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.dto.IpQueryDTO;
import com.duzy.service.IpLocationService;
import com.duzy.vo.IpVo;
import com.duzy.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zhiyuandu
 * @since 2023/1/7 16:39
 * @description
 */
@RestController
@RequestMapping("/api/ip")
@Tag(name = "log查询")
public class IpController {
    @Autowired
    IpLocationService ipService;

    @GetMapping
    @Operation(summary = "log查询", description = "分页查询ssh日志")
    public ResultVO<Page<IpVo>> ip(IpQueryDTO queryDTO) {
        Page<IpVo> result = ipService.list(queryDTO);
        return ResultVO.SUCCESS(result);
    }
}
