package com.duzy.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhiyuandu
 * @date 2021/12/16-14:46
 * @description swagger配置
 **/

@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {

    /**
     * 创建RestApi 并包扫描controller
     *
     * @return
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.duzy.controller")).paths(PathSelectors.any()).build();
    }


    /**
     * 创建Swagger页面 信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("dapi接口文档").version("1.0").license("no-license").licenseUrl("").termsOfServiceUrl("").description("dapi接口文档description").build();
    }

}
