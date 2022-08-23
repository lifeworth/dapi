package com.duzy;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableSpringUtil
@EnableAspectJAutoProxy
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
