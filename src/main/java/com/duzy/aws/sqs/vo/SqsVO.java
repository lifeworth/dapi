package com.duzy.aws.sqs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhiyuandu
 * @since 2022/8/28 18:10
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SqsVO implements Serializable {
    private static final long serialVersionUID = -8013965441896177936L;
    public String url;
    public String content;
    public Date date;
}
