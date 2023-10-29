package com.nacos.tutorial.kernel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient // 开启服务注册与发现
@EnableFeignClients("com.nacos.tutorial.kernel.feign.client") // 开启Feign，并让接口被启动类扫描到
public class PayStarterApp {
    public static void main(String[] args) {
        SpringApplication.run(PayStarterApp.class, args);
    }
}