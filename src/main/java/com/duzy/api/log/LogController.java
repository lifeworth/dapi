package com.duzy.api.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.dto.SshLogQueryDTO;
import com.duzy.service.LogService;
import com.duzy.vo.ResultVO;
import com.duzy.vo.SshLogVo;
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
@RequestMapping("/api/log")
public class LogController {
    @Autowired
    LogService logService;

    @GetMapping("/ssh")
    public ResultVO<Page<SshLogVo>> login(SshLogQueryDTO queryDTO) {
        Page<SshLogVo> result = logService.listSsh(queryDTO);
        return ResultVO.SUCCESS(result);
    }
}
