package com.duzy.common.config;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zhiyuandu
 * @description 日志中显示IP
 * @since 2022/2/13 21:31
 */
@Slf4j
public class IpLogConfig extends ClassicConverter {
    /**
     * The convert method is responsible for extracting data from the event and
     * storing it for later use by the write method.
     *
     * @param event
     */
    @Override
    public String convert(ILoggingEvent event) {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error(Throwables.getStackTraceAsString(e));
        }
        return null;
    }
}
