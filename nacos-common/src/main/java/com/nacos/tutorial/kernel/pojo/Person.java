package com.nacos.tutorial.kernel.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 19zfl
 * @description: Person实体类
 * @date 2023-09-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 地址
     */
    private String addr;

}
