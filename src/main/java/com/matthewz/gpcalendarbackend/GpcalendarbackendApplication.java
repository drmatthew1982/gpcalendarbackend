package com.matthewz.gpcalendarbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.util.TimeZone;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
@MapperScan(basePackages = {"com.matthewz.gpcalendarbackend.mapper"})
public class GpcalendarbackendApplication {

    public static void main(String[] args) {
        System.out.println(TimeZone.getDefault());
        SpringApplication.run(GpcalendarbackendApplication.class, args);
    }

}
