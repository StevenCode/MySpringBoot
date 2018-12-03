package com.github.steven.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableFeignClients
public class SpringCloudAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAccountApplication.class, args);
    }
}
