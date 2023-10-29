package com.nacos.tutorial.kernel.controller;

import com.nacos.tutorial.kernel.feign.client.ServerPersonFeignClient;
import com.nacos.tutorial.kernel.pojo.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: 19zfl
 * @description: nacos-pay服务控制层
 * @date 2023-10-26
 */

@RestController
@RequestMapping("/pay")
public class PayController {

    @Resource
    private ServerPersonFeignClient serverPersonFeignClient;

    @GetMapping("/getPersonInfoById/{id}")
    public Person getPersonInfoById(@PathVariable Long id) {
        return serverPersonFeignClient.getPersonInfoById(id);
    }

}
