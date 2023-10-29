package com.nacos.tutorial.kernel.feign.fallback;

import com.nacos.tutorial.kernel.feign.client.ServerPersonFeignClient;
import com.nacos.tutorial.kernel.pojo.Person;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: 19zfl
 * @description: 回滚方法
 * @date 2023-10-29
 */

@Component
public class PersonFeignClientFallback implements FallbackFactory<ServerPersonFeignClient> {
    @Override
    public ServerPersonFeignClient create(Throwable throwable) {
        return new ServerPersonFeignClient() {
            @Override
            public Person getPersonInfoById(Long id) {
                throwable.printStackTrace();
                return new Person(0000L, "网络异常！", "0000");
            }
        };
    }
}
