package com.duzy.common.handler;

import cn.hutool.core.util.StrUtil;
import com.duzy.common.exception.BizException;
import com.duzy.vo.ResultVO;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.BufferedReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhiyuandu
 * @date 2021/12/21-15:46
 * @description Controlelr 全局异常处理
 **/
@RestControllerAdvice
@Slf4j
public class ControllerAdvice {


    /**
     * 处理所有校验失败的异常（MethodArgumentNotValidException异常）
     * 设置响应状态码为400
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultVO methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        log.error("拦截到参数校验异常:{}", Throwables.getStackTraceAsString(ex));
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map((fieldError) -> StrUtil.format("字段:[{}],错误信息:[{}]", fieldError.getField(),
                        fieldError.getDefaultMessage())).toList();
        return ResultVO.FAIL(HttpStatus.BAD_REQUEST.value(), errors.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public ResultVO bizExceptionHandler(BizException ex) {
        log.error("业务异常:{}", Throwables.getStackTraceAsString(ex));
        return ResultVO.FAIL(ex.getCode(), ex.getMessage());
    }

    /**
     * 处理所有异常（Exception异常）
     * 设置响应状态码为500
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {Exception.class, Throwable.class})
    public ResultVO errorHandler(Throwable ex) {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) (Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes()))).getRequest();
        Map<String, Object> req = getReq();
        String format = StrUtil.format("全局异常拦截 拦截到异常:{}，请求:{}", ex.getMessage(), SecurityUserContext.getUserInfo().getNick(),
                httpServletRequest.getRequestURI(), httpServletRequest.getParameterMap());
        log.error(format, req);
        return ResultVO.FAIL(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {RuntimeException.class})
    public ResultVO runtimeErrorHandler(Throwable ex) {
        Map<String, Object> req = getReq();
        log.error("全局异常拦截 拦截到runtime异常:{}，请求:{}", Throwables.getStackTraceAsString(ex), req);
        return ResultVO.FAIL(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    public Map<String, Object> getReq() {
        HashMap<String, Object> result = Maps.newHashMap();
        try {
            HttpServletRequest httpServletRequest = ((ServletRequestAttributes) (Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))).getRequest();
            StringBuffer requestURL = httpServletRequest.getRequestURL();
            Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
            StringBuffer stringBuffer = new StringBuffer();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = httpServletRequest.getHeader(headerName);
                stringBuffer.append(StrUtil.format("{}:{}.", headerName, headerValue));
            }
            String contentType = httpServletRequest.getContentType();
            String method = httpServletRequest.getMethod();
            Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
            String characterEncoding = httpServletRequest.getCharacterEncoding();
            String pathInfo = httpServletRequest.getPathInfo();
            int contentLength = httpServletRequest.getContentLength();
            String queryString = httpServletRequest.getQueryString();
            String remoteAddr = httpServletRequest.getRemoteAddr();
            StringBuffer jb = new StringBuffer();
            String line = null;
            BufferedReader reader = httpServletRequest.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }

            result.put("requestURL", requestURL);
            result.put("contentType", contentType);
            result.put("method", method);
            result.put("body", jb);
            result.put("parameterMap", parameterMap);
            result.put("headers", stringBuffer.toString());
            result.put("characterEncoding", characterEncoding);
            result.put("pathInfo", pathInfo);
            result.put("contentLength", contentLength);
            result.put("queryString", queryString);
            result.put("remoteAddr", remoteAddr);
        } catch (Exception e) {
            log.error("获取请求信息出错.{}", Throwables.getStackTraceAsString(e));
        }
        return result;
    }

}
