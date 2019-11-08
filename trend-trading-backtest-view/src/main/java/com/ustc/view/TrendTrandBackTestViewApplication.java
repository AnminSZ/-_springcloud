package com.ustc.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TrendTrandBackTestViewApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrendTrandBackTestViewApplication.class, args);
    }
}
