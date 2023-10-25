package com.nacos.tutorial.kernel.controller;

import com.nacos.tutorial.kernel.pojo.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 19zfl
 * @description: 服务端控制层
 * @date 2023-09-27
 */
@RestController
@RequestMapping("/server")
public class ServerController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/getPersonInfoById/{id}")
    public Person getPersonInfoById(@PathVariable Long id) {
        return new Person(id, "刘十三", "地址在：" + port);
    }

}
