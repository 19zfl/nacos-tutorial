package com.nacos.tutorial.kernel.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 19zfl
 * @description: 开启更改负载均衡策略配置类
 * @date 2023-10-25
 */

@Configuration
public class RibbonConfig {

    @Bean
    public IRule iRule() {
        return new NacosRule();
    }

}
