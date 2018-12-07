package com.github.steven.inventory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.github.steven.inventory.mapper")
public class SpringCloudInventoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudInventoryApplication.class, args);
    }
}
