package com.matthewz.gpcalendarbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.matthewz.gpcalendarbackend.mapper"})
public class GpcalendarbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpcalendarbackendApplication.class, args);
    }

}
