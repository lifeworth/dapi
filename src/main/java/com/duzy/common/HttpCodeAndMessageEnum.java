package com.duzy.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author zhiyuandu
 * @date 2021/12/20-15:49
 * @description http返回消息枚举
 **/
@AllArgsConstructor
public enum HttpCodeAndMessageEnum {

    /**
     * 操作成功
     */
    SUCCESS(1, "操作成功"),
    /**
     * 查询无数据
     */
    NO_DATA(1, "数据不存在"),
    /**
     * 新增失败
     */
    INSERT_ERROR(20, "新增失败,中文名称 或 英文名称 不能和已有的数据重复."),
    /**
     * 删除失败
     */
    DELETE_ERROR(3, "删除失败,存在相关联的数据."),
    /**
     * 更新失败
     */
    UPDATE_ERROR(4, "更新失败,中文名称 或 英文名称 不能和已有的数据重复."),
    /**
     *
     */
    OPERATION_FAILED(6, "操作失败"),


    /**
     * 500 服务器端错误
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器端错误"),
    /**
     * 500 文件写入失败
     */

    FILE_WRITE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "文件写入失败"),
    /**
     * 自定义业务异常
     */
    BIZ_ERROR_NOT_FOUND(5, "数据不存在");

    /**
     * 消息编码
     */
    @Getter
    private final int code;
    /**
     * 消息内容
     */
    @Getter
    private final String message;


}
