package com.duzy.common.aop;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * controller请求计时
 * @author zhiyuandu
 */
@Aspect
@Component
@Slf4j
public class ControllerAop {
    @Pointcut("execution(com.duzy.vo.ResultVO com.duzy.controller..*.*(..) )")
    public void consumeTime() {
    }

    @Around(value = "consumeTime()")
    public Object updateTime(ProceedingJoinPoint point) throws Throwable {
        Object object;
        Map<String, Object> nameAndValue = getNameAndValue(point);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.start();
        object = point.proceed();
        String result = "";
        if (Objects.nonNull(object)) {
            result = object.toString();
            if (result.length() > 100) {
                result = StrUtil.subByCodePoint(result, 0, 99);
            } else {
                result = StrUtil.subByCodePoint(result, 0, result.length());
            }
        }

        log.info("方法参数:{},返回结果：{},处理时间:{}ms", nameAndValue, result, timeInterval.intervalMs());
        return object;
    }

    /**
     * 获取方法参数
     * @param joinPoint
     * @return
     */
    Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {
        Object[] paramValues = joinPoint.getArgs();
        Map<String, Object> param = new HashMap<>(paramValues.length);
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        if (Objects.nonNull(paramNames)) {
            for (int i = 0; i < paramNames.length; i++) {
                param.put(paramNames[i], paramValues[i]);
            }
        }
        return param;
    }
}
