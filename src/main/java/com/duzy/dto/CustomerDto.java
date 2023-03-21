package com.duzy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author zhiyuandu
 * @since 2023/3/20 11:09
 * @description
 */
@Data
public class CustomerDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "自增id")
    private Integer id;

}
