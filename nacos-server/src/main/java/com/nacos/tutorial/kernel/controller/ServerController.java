package com.nacos.tutorial.kernel.controller;

import com.nacos.tutorial.kernel.pojo.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.sampled.Port;

/**
 * @author: 19zfl
 * @description: 服务端控制层
 * @date 2023-09-27
 */
@RestController
@RequestMapping("/server")
@RefreshScope // 动态加载配置文件
public class ServerController {

    @Value("${server.port}")
    private String port;

//    @Value("${temp.value}")
//    private String value;

    @GetMapping("/getPersonInfoById/{id}")
    public Person getPersonInfoById(@PathVariable Long id) {
        return new Person(id, "temp:" + port, "port：" + port);
    }

}
