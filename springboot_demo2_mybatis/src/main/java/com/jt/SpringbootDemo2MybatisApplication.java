package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jt.dao")
public class SpringbootDemo2MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo2MybatisApplication.class, args);
    }

}
