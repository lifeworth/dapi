package com.duzy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.dto.SshLogQueryDTO;
import com.duzy.service.SshLogService;
import com.duzy.vo.ResultVO;
import com.duzy.vo.SshLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhiyuandu
 * @since 2022/11/21 17:42
 * @description
 */
@RestController
@RequestMapping("/api/sshLog")
public class SshLogController {
    @Autowired
    SshLogService sshLogService;

    @PostMapping("/page")
    public ResultVO<Page<SshLogVo>> page(@RequestBody SshLogQueryDTO queryDTO) {
        Page<SshLogVo> result = sshLogService.page(queryDTO);
        return ResultVO.SUCCESS(result);
    }
}
