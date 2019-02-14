package com.github.steven.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ImportResource({"classpath:applicationContext.xml"})
@MapperScan("com.github.steven.order.mapper")
public class SpringcloudOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringcloudOrderApplication.class, args);
    }
}
