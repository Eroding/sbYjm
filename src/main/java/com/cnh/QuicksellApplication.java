package com.cnh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;


@MapperScan(basePackages = "com.cnh.dao")
//加载验证码这个xml
@ImportResource(locations = {"classpath:kaptcha.xml"})
@SpringBootApplication
public class QuicksellApplication /*extends SpringBootServletInitializer*/ {

    public static void main(String[] args) {

        SpringApplication.run(QuicksellApplication.class, args);
    }
//当以war包要放tomcat服务器下的时候需要加这条命令
  /*  @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(QuicksellApplication.class);
    }*/
}
