package com.duzy.vo;

import cn.hutool.core.date.DateTime;
import com.duzy.common.HttpCodeAndMessageEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhiyuandu
 * @date 2021/12/20-15:36
 * @description TODO
 **/
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("返回VO")
@Slf4j
public class ResultVO<T> {

    @ApiModelProperty("操作是否成功")
    private Boolean success;

    @ApiModelProperty("错误代码 1成功 ")
    private Integer code;

    @ApiModelProperty("错误信息")
    private String message;

    @ApiModelProperty("返回内容")
    private T data;

    @ApiModelProperty("返回时间")
    private Long timeStamp;

    public static ResultVO<Object> SUCCESS() {
        return new ResultVO<>(true, HttpCodeAndMessageEnum.SUCCESS.getCode(),
                              HttpCodeAndMessageEnum.SUCCESS.getMessage(), null, DateTime.now().getTime());
    }

    public static <T> ResultVO<T> SUCCESS(T t) {
        return new ResultVO<>(true, HttpCodeAndMessageEnum.SUCCESS.getCode(),
                              HttpCodeAndMessageEnum.SUCCESS.getMessage(), t, DateTime.now().getTime());
    }

    public static <T> ResultVO<T> SUCCESS(HttpCodeAndMessageEnum httpCodeAndMessageEnum) {
        return new ResultVO<>(true, httpCodeAndMessageEnum.getCode(), httpCodeAndMessageEnum.getMessage(), null,
                              DateTime.now().getTime());
    }

    public static <T> ResultVO<T> SUCCESS(T t, String message) {
        return new ResultVO<>(true, HttpCodeAndMessageEnum.SUCCESS.getCode(), message, t, DateTime.now().getTime());
    }

    public static ResultVO<Object> FAIL() {
        return new ResultVO<>(false, HttpCodeAndMessageEnum.INTERNAL_SERVER_ERROR.getCode(),
                              HttpCodeAndMessageEnum.INTERNAL_SERVER_ERROR.getMessage(), null,
                              DateTime.now().getTime());
    }

    public static ResultVO FAIL(HttpCodeAndMessageEnum httpCodeAndMessageEnum) {
        return new ResultVO<>(false, httpCodeAndMessageEnum.getCode(), httpCodeAndMessageEnum.getMessage(), null,
                              DateTime.now().getTime());
    }

    public static ResultVO<Object> FAIL(int code, String errorMessage) {
        return new ResultVO<>(false, code, errorMessage, null, DateTime.now().getTime());
    }


}
