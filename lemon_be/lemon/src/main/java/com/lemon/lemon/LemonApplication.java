package com.lemon.lemon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lemon.lemon.mapper")
public class LemonApplication {

    public static void main(String[] args) {
        SpringApplication.run(LemonApplication.class, args);
    }

}
