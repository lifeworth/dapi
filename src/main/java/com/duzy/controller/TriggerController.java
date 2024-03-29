package com.duzy.controller;

import com.duzy.service.IpLocationService;
import com.duzy.service.LogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiyuandu
 * @description
 * @since 2022/10/31 17:23
 */
@RestController
@RequestMapping("/log")
@Slf4j
@Tag(name = "任务触发器")
public class TriggerController {

    @Autowired
    private IpLocationService ipLocationService;
    @Autowired
    private LogService logService;


    @GetMapping("/ssh")
    public void ssh() {
        logService.loadSshLogFileToDb();
    }

    @GetMapping("/loadSsh")
    public void load() {
        logService.loadSshSource();
    }

    @GetMapping("/nginx")
    public void nginx() {
        logService.loadNginxLogFileToDb();
    }

    @GetMapping("/ssh/trans")
    public void sshTrans() {
        logService.sshTrans();
    }

    @GetMapping("/nginx/trans")
    public void nginxTrans() {
        logService.nginxTrans();
    }

    @GetMapping("/ip")
    public void location() {
        ipLocationService.parseFromApi();
    }


}
