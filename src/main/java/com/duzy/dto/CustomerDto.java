package com.duzy.dto;

import com.duzy.dto.validater.CreateValidGroup;
import com.duzy.dto.validater.UpdateValidGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "id不能为空", groups = {UpdateValidGroup.class})
    @NotNull(message = "id必须为空", groups = {CreateValidGroup.class})
    private Long id;

}
