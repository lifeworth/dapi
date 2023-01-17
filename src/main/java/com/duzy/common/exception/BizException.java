package com.duzy.common.exception;

import com.duzy.common.enums.HttpCodeAndMessageEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author zhiyuandu
 * @date 2021/12/21-18:42
 * @description TODO
 **/
@Getter
@Setter
@Component
@ToString
public class BizException extends RuntimeException {
    private int code;
    private String message;

    public BizException() {
        super();
    }

    public BizException(String msg) {
        super(msg);
    }

    public BizException(HttpCodeAndMessageEnum httpCodeAndMessageEnum) {
        super(httpCodeAndMessageEnum.getMessage());
        this.code = httpCodeAndMessageEnum.getCode();
        this.message = httpCodeAndMessageEnum.getMessage();
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
