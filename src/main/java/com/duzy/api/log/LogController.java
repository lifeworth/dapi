package com.duzy.api.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.dto.NginxLogQueryDTO;
import com.duzy.dto.SshLogQueryDTO;
import com.duzy.service.LogService;
import com.duzy.vo.NginxLogVO;
import com.duzy.vo.ResultVO;
import com.duzy.vo.SshLogVo;
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
@RequestMapping("/api/log")
@Tag(name = "log查询")
public class LogController {
    @Autowired
    LogService logService;

    @GetMapping("/ssh")
    @Operation(summary = "log查询", description = "分页查询ssh日志")
    public ResultVO<Page<SshLogVo>> sshLog(SshLogQueryDTO queryDTO) {
        Page<SshLogVo> result = logService.listSsh(queryDTO);
        return ResultVO.SUCCESS(result);
    }

    @GetMapping("/nginx")
    @Operation(summary = "log查询", description = "分页查询nginx日志")
    public ResultVO<Page<NginxLogVO>> nginxLog(NginxLogQueryDTO queryDTO) {
        Page<NginxLogVO> result = logService.listNginx(queryDTO);
        return ResultVO.SUCCESS(result);
    }
}
