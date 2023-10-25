package com.nacos.tutorial.kernel.controller;

import com.nacos.tutorial.kernel.pojo.Person;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author: 19zfl
 * @description: 客户端控制层
 * @date 2023-10-18
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/getPersonInfoById/{id}")
    public Person getPersonInfoById(@PathVariable Long id) {
        String url = "http://nacos-server/server/getPersonInfoById/" + id;
        return restTemplate.getForObject(url, Person.class);
    }

}
