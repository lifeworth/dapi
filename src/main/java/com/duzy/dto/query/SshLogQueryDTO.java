package com.duzy.dto.query;

import com.duzy.dto.BaseQueryDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author zhiyuandu
 * @since 2022/11/21 17:54
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "ssh日志查询对象")
public class SshLogQueryDTO extends BaseQueryDTO {


    private String ip;

    private Integer id;

    private String source;

}
