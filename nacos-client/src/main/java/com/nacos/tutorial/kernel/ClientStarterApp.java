package com.nacos.tutorial.kernel;

import com.nacos.tutorial.kernel.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author: 19zfl
 * @description: 客户端启动类
 * @date 2023-09-27
 */
@SpringBootApplication
@EnableDiscoveryClient // 开启服务注册与发现
@RibbonClients(value = {
        @RibbonClient(name = "nacos-server", configuration = RibbonConfig.class)
}) // 启用配置类
public class ClientStarterApp {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientStarterApp.class, args);
    }

}
