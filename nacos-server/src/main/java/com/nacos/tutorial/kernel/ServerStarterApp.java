package com.nacos.tutorial.kernel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: 19zfl
 * @description: 服务端启动类
 * @date 2023-09-27
 */
@SpringBootApplication
@EnableDiscoveryClient // 开启服务注册与发现
public class ServerStarterApp {
    public static void main(String[] args) {
        SpringApplication.run(ServerStarterApp.class, args);
    }
}
