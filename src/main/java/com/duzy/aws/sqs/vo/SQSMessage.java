package com.duzy.aws.sqs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhiyuandu
 * @since 2022/8/27 23:08
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SQSMessage implements Serializable {

    private static final long serialVersionUID = -8013965441896177936L;
    private String id;
    private String content;
    private Date sendDate;
}
