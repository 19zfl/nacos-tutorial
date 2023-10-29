package com.nacos.tutorial.kernel.feign.client;

import com.nacos.tutorial.kernel.pojo.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("nacos-server") // 指向该接口通信的服务名称
public interface ServerPersonFeignClient {

    /**
     * @description nacos-server服务中ServerController中的一个请求方法
     * @param id
     * @return Person数据
     */
    @GetMapping("/server/getPersonInfoById/{id}") // 需要将url路径地址写完整
    Person getPersonInfoById(@PathVariable Long id);

}
