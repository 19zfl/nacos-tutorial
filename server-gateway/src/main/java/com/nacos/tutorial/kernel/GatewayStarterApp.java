package com.nacos.tutorial.kernel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName:GatewayStarterApp
 * @Description: gateway启动类
 * @Author:zfl19
 * @CreateDate:2024/4/8 18:16
 */

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayStarterApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayStarterApp.class, args);
    }
}
