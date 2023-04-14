package com.duzy.common.config;

import com.duzy.common.handler.SecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhiyuandu
 * @date 2022/1/7-11:44
 * @description WebConfig
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        //对那些请求路径进行跨域处理
        registry.addMapping("/**")
                //允许的请求头，默认允许所有的请求头
                .allowedHeaders("*")
                //允许的方法，默认允许GET、POST、HEAD
                .allowedMethods("*")
                //探测请求有效时间，单位秒
                .maxAge(1800)
                .allowCredentials(false)
                //支持的域
                .allowedOrigins("*");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/**");
    }


    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }


}
