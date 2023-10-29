# SpringCloudAlibaba-Nacos教程

### 一、Nacos官网

#####  地址：https://nacos.io/zh-cn/index.html

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202309262246008.png)

##### 下载地址：[Releases · alibaba/nacos · GitHub](https://github.com/alibaba/nacos/releases)

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202309262247480.png)

### 二、Nacos介绍

#### 1. 为什么要使用SpringCloudAlibaba？原生SpringCloud不能用？

原生SpringCloud可以用，不用是因为国内微服务项目大部分都已经在使用SpringCloudAlibaba了，因为她更好用，很符合国内环境，她提供的Nacos就是代替原生Eureka，Eureka现在已经不再维护了。

#### 2. 官方概述：

Spring Cloud Alibaba旨在为微服务开发提供一站式解决方案。该项目包括开发分布式应用程序和服务所需的组件，以便开发人员可以使用Spring Cloud编程模型轻松开发分布式应用程序。使用Spring Cloud Alibaba，您只需要添加一些注释和配置，就可以为您的应用程序使用Alibaba的分布式解决方案，并使用Alibaba中间件构建自己的分布式系统。

### 三、Nacos使用

#### 1.Nacos安装

将下载好的zip压缩包解压到磁盘中

#### 2. Nacos运行

进入bin目录，Nacos默认是集群方式启动，所以双击startup.cmd启动Nacos是一定会报错的

在地址行输入cmd，进入命令行窗口，输入一下指令即可：

```cmd
startup.cmd -m standalone
```

启动成功是这样的：

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202309270853965.png)

浏览器访问：

```chrome
http://127.0.0.1:8848/nacos/index.html
```

打开应该是这样的：

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202309270912493.png)

如果需要登录是Nacos版本低了，账号密码都是nacos

目前为止，Nacos的服务端就已经安装好了。

#### 3. Nacos项目案例搭建

项目结构如下：

````idea
nacos-tutorial // 父目录，包含两个子模块
	nacos-server	// 服务端，port=1010
	nacos-client	// 客户端，port=1020
pom.xml
````

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202309270929765.png)

父目录pom.xml：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.nacos.tutorial.kernel</groupId>
    <artifactId>nacos-tutorial</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>
    <modules>
        <module>nacos-server</module>
        <module>nacos-client</module>
    </modules>

    <developers>
        <developer>
            <name>19zfl</name>
            <email>19aleiya@gmail.com</email>
            <roles>
                <role>developer</role>
            </roles>
            <timezone>+8</timezone>
        </developer>
    </developers>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <!-- 管理SpringBoot的依赖 -->
    <parent>
        <groupId> org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.13.RELEASE</version>
    </parent>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.1.1.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Greenwich.SR2</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
    </dependencies>

</project>
```

客户端和服务端的pom.xml新增以下：

```xml
	<dependencies>

        <!--服务注册与发现-->
        <dependency>
            <groupId>com.alibaba.cloud </groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

        <!--web基础依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
    </dependencies>
```

服务端与客户端启动类

```java
@SpringBootApplication
@EnableDiscoveryClient // 开启服务注册与发现
public class XXXStarterApp {
    public static void main(String[] args) {
        SpringApplication.run(XXXStarterApp.class, args);
    }
}
```

服务端与客户端配置文件application.yml

```xml
server:
  port: xxxx
spring:
  application:
    name: nacos-xxxx
  cloud:
    nacos:
      discovery:
        # Nacos 注册中心地址
        server-addr: 127.0.0.1:8848
```

启动服务查看Nacos网站：

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202309271837000.png)

图中可以看到服务端和客户端已经注册到Nacos注册中心了。

### 四、Ribbon服务通信

#### 1.Ribbon概念

Ribbon是Netflix发布的云中间层服务开源项目，主要功能是提供客户端负载均衡算法。Ribbon客户端组件提供一系列完善的配置项，如，连接超时，重试等。
简单来说，Ribbon是一个客户端负载均衡器，Ribbon可以按照负载均衡算法(如简单轮询，随机连接等)向多个服务发起调用，我们也很容易使用Ribbon实现自定义的负载均衡算法。

#### 2.服务通信实现

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202309271922770.png)

新建一个子模块nacos-common，服务端客户端都需要依赖，抽离公共模块，减少代码冗余，然后新建一个实体类Person：

```Person.java
package com.nacos.tutorial.kernel.pojo;

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
```

在服务端和客户端的pom.xml文件中新增以下代码：

```xml
		<dependency>
            <groupId>com.nacos.tutorial.kernel</groupId>
            <artifactId>nacos-common</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

编写服务端控制层ServerController.java

```ServerController.java
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
```

编写服务端控制层ClientController.java

```ClientController.java
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
        String url = "http:/nacos-server/server/getPersonInfoById/" + id;
        return restTemplate.getForObject(url, Person.class);
    }

}
```

需要在启动类中定义RestTemplate对象，使用**@LoadBalanced**开启负载均衡，Nacos默认整合了Ribbon，启动类修改如下：

```ClientStarterApp.java
package com.nacos.tutorial.kernel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author: 19zfl
 * @description: 客户端启动类
 * @date 2023-09-27
 */
@SpringBootApplication
@EnableDiscoveryClient // 开启服务注册与发现
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
```

添加了 @LoadBalanced 注解之后，Ribbon会给RestTemplate请求添加一个拦截器，在拦截器中获取注册中心的服务列表，并使用Ribbon内置的负载均衡算法从服务列表里选中一个服务，通过获取到的服务信息 （ip,port）替换 ServiceId 实现负载请求。

#### 3.服务之间通信测试

启动服务端和客户端，在浏览器地址栏输入：http://localhost:1020/client/getPersonInfoById/11，出现如下图所示代表通信成功：

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202310251932335.png)

不难理解，这条数据是从服务端获取到的，在浏览器输入：http://localhost:1010/server/getPersonInfoById/11，也会出现此信息：

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202310251937634.png)

#### 4.负载均衡

此时一个服务端显然不能够看到负载均衡的效果，需要给服务端做一个集群设置【IntelliJIDEA 2023.1 为例】。

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202310251946840.png)

然后我们启动这个服务，看nacos官网有没有显示这个服务；

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202310251948389.png)

点击详情；

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202310251950090.png)

以上就是将某个服务进行集群的操作；

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202310251957388.png)

由此可见，我们已经成功将服务端进行了集群，并且在客户端调用服务端的时候，我们不断刷新网页，可以发现端口号是一直在变化的，会轮询显示1010/1030，说明Ribbon的默认负载均衡策略是轮询。

#### 5.更改负载均衡策略

仅仅只需要一个配置类就可以让我们进行更改负载均衡的策略，配置类如下：

```RibbonConfig.java
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
```

这个配置类不是全局配置，是局部配置，需要在客户端启动类中启动该配置类，在指定的服务中生效；

全局配置只需要将配置类与启动类同包（让启动类能够扫描到）即可，所有的服务都能生效；

在服务端启动类打上如下注解：

```
@RibbonClients(value = {
        @RibbonClient(name = "nacos-server", configuration = RibbonConfig.class)
}) // 启用配置类
```

测试【重启客户端】：

将其中一个权重修改一下，这里将1030端口的服务权重修改为3；

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202310252038925.png)

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202310252040011.png)

如图所示，点击确定后，页面报错了，修改失败；

解决问题：

- 将nacos服务停止
- 在nacos目录中找到protocal文件夹【data-protocal】并删除掉

再次启动nacos，修改权重，修改完刷新页面就可以看到已经修改成功了：

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202310252044559.png)

这里将端口1030的服务权重修改为3，说明它的请求流量是1010的3倍，待会请求的时候，如果可以看到json数据中，有75%左右的概率是1030（1030显示比1010多），就说明更改Ribbon负载均衡策略成功了。

### 五、Feign服务通信

#### 1. Feign介绍：

Feign是Netflix公司开发的一个声明式的REST调用客户端；

Feign是一个声明式的http客户端，使用Feign可以实现声明式REST调用，它的目的就是让Web Service调用更加简单

Feign整合了Ribbon和SpringMvc注解，这让Feign的客户端接口看起来就像一个Controller

Feign提供了HTTP请求的模板，通过编写简单的接口和插入注解，就可以定义好HTTP请求的参数、格式、地址等信息

而Feign则会完全代理HTTP请求，我们只需要像调用方法一样调用它就可以完成服务请求及相关处理

同时Feign整合了Hystrix，可以很容易的实现服务熔断和降级

<font color=red>tip:别的一大堆概念不需要记住，现在只需要知道Feign是什么，遇到什么再去学习</font>

#### 2.Feign与Ribbon的联系：

Ribbon是一个基于 HTTP 和 TCP 客户端 的负载均衡的工具。它可以 在客户端配置

RibbonServerList(服务端列表)，使用 HttpClient 或 RestTemplate 模拟http请求，步骤相当繁琐。

Feign 是在 Ribbon的基础上进行了一次改进，是一个使用起来更加方便的 HTTP 客户端。采用接口的 方式， 只需要创建一个接口，然后在上面添加注解即可 ，将需要调用的其他服务的方法定义成抽象方 法即可， 不需要自己构建http请求。然后就像是调用自身工程的方法调用，而感觉不到是调用远程方 法，使得编写客户端变得非常容易。

#### 3.为什么要使用Feign？

Feign基于Ribbon进行了封装，把一些URL拼接和参数处理细节进行屏蔽了，我们只需要编写Feign的客户端接口，就可以像调用本地方法去调用远程微服务方法；

#### 4.实战编码

现在就是用Feign来完成不同服务之间的调用了，需要新建一个支付服务：nacos-pay，支付服务充当的就是之前的客户端服务；

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202310261350359.png)

##### 新建支付服务nacos-pay

###### pom.xml相关配置：

```pom.xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.nacos.tutorial.kernel</groupId>
        <artifactId>nacos-tutorial</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>nacos-pay</artifactId>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!--服务注册与发现-->
        <dependency>
            <groupId>com.alibaba.cloud </groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

        <!--加入web依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--Feign依赖-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>

        <!--nacos-common模块-->
        <dependency>
            <groupId>com.nacos.tutorial.kernel</groupId>
            <artifactId>nacos-common</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

</project>
```

###### 启动类：

```PayStarterApp.java
package com.nacos.tutorial.kernel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient // 开启服务注册与发现
@EnableFeignClients("com.nacos.tutorial.kernel.feign.client") // 开启Feign，并让接口被启动类扫描到
public class PayStarterApp {
    public static void main(String[] args) {
        SpringApplication.run(PayStarterApp.class, args);
    }
}
```

###### 支付服务配置文件

```application.yml
server:
  port: 1040
spring:
  application:
    name: nacos-pay
  cloud:
    nacos:
      discovery:
        # Nacos 注册中心地址
        server-addr: 127.0.0.1:8848
```

###### Feign接口：

```ServerPersonFeignClient.java
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
    @GetMapping("/server/getPersonInfoById/{id}") // 这里需要将url路径写完整
    Person getPersonInfoById(@PathVariable Long id);

}
```

###### 控制层：

```PayController.java
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
```

##### 测试Feign接口：

启动服务端和支付端，然后浏览器访问支付端的接口：http://localhost:1040/pay/getPersonInfoById/11,返回结果如下：

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202310292324238.png)

出现如图所示结果就说明使用Feign来完成服务之间的通信没有问题。

#### 5.fallback

fallback翻译过来就是回滚的意思，比如支付服务与服务端通信的时候由于某种原因调用服务端的接口失败，总不能将类似下图这种信息直接返回给用户吧，为了更友好的展示给用户，需要编写一个回滚方法。

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202310292334562.png)

##### 编写回滚类：

```PersonFeignClientFallback.java
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
```

##### Feign接口开启回滚并指向对应fallback类：

```java
@FeignClient(value = "nacos-server", fallbackFactory = PersonFeignClientFallback.class)
// 只需要在之前的注解中添加fallbackFactory属性，属性值为回滚类.class
```

##### yml开启Feign-hystrix：

```yml
feign:
  hystrix:
    enabled: true # Feign是包含hystrix的，但默认是关闭的，开启需要添加此段配置
```

##### 测试：

当我们的服务端出现问题无法访问的时候（服务端关闭），就会调用回滚方法，如图所示就代表Feign的熔断功能开启成功：

![](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202310300001014.png)

### 六、Nacos功能
