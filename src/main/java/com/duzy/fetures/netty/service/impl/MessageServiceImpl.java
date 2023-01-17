package com.duzy.fetures.netty.service.impl;

import com.duzy.fetures.netty.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhiyuandu
 * @since 2023/1/15 03:55
 * @description
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {


    @Override
    public void discard(String text) {
        log.info("Discarding:{}",text);
    }
}
