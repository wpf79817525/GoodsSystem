package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
@MapperScan("com.mapper")
public class SpringBootProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProjectApplication.class, args);
    }

}
