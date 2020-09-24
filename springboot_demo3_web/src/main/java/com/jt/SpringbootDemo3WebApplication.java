package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jt.dao")
public class SpringbootDemo3WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo3WebApplication.class, args);
    }

}
