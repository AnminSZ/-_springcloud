package com.ustc.zuul;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//@EnableDiscoveryClient
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class IndexZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(IndexZuulApplication.class, args);
    }
}
