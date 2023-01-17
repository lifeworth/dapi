package com.duzy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TestSocketMsg implements Serializable {
    @Serial
    private static final long serialVersionUID = -3348820821463060669L;

    private String msg;

    private Integer type;
}
