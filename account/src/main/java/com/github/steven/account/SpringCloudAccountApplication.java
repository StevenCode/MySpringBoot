package com.github.steven.account;

//import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.github.steven.account.mapper")
public class SpringCloudAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAccountApplication.class, args);
    }
}
