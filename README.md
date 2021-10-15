# springcloud-test-root

> 官方文档

* [Spring Boot 官网](https://spring.io/projects/spring-boot)
* [Spring Cloud 官网](https://spring.io/projects/spring-cloud)
* [Spring Cloud Alibaba 官网](https://spring.io/projects/spring-cloud-alibaba)


## springcloud 全家桶(组件)示例

- [cloud-config 配置管理](cloud-config) 
- [cloud-eureka 服务管理](cloud-eureka)   
- [cloud-gateway API网关](cloud-gateway)    
- [cloud-nacos 配置和服务管理](cloud-nacos)      
- [cloud-zookeeper 服务管理](cloud-zookeeper)     


## springboot、spring cloud、spring-cloud-alibaba 版本对应
**Release Train**

| spring-cloud Release Train                                   | spring-boot Version                          |                spring-cloud-alibaba  Version         |
| :----------------------------------------------------------- | :------------------------------------ | :-------------------------------------------------- |
| [2020.0.x](https://github.com/spring-cloud/spring-cloud-release/wiki/Spring-Cloud-2020.0-Release-Notes) aka Ilford | 2.4.x, 2.5.x (Starting with 2020.0.3) |  2020.x    |
| [Hoxton](https://github.com/spring-cloud/spring-cloud-release/wiki/Spring-Cloud-Hoxton-Release-Notes) | 2.2.x, 2.3.x (Starting with SR5)      |   2.2.x            |
| [Greenwich](https://github.com/spring-projects/spring-cloud/wiki/Spring-Cloud-Greenwich-Release-Notes) | 2.1.x                                 |  2.1.x        |  
| [Finchley](https://github.com/spring-projects/spring-cloud/wiki/Spring-Cloud-Finchley-Release-Notes) | 2.0.x                                 |    2.0.x        |
| [Edgware](https://github.com/spring-projects/spring-cloud/wiki/Spring-Cloud-Edgware-Release-Notes) | 1.5.x                                 |      1.5.x        |
| [Dalston](https://github.com/spring-projects/spring-cloud/wiki/Spring-Cloud-Dalston-Release-Notes) | 1.5.x                                 |     1.5.x         |

> Spring Cloud Dalston, Edgware, Finchley, 和 Greenwich 都已达到生命周期终止状态，不再受支持


添加

```xml
<properties>
        <!--spring-boot-->
        <spring-boot.version>2.2.5</spring-boot.version>
        <!--spring-cloud-->
        <spring-cloud.version>2020.0.4</spring-cloud.version>
        <!--spring-cloud-alibaba-->
        <spring-cloud-alibaba-dependencies.version>2.2.6.RELEASE</spring-cloud-alibaba-dependencies.version>
</properties>

<dependencyManagement>
    <dependencies>
        <!--spring-boot-dependencies-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>${spring-boot-dependencies.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <!--spring-cloud-dependencies-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud-dependencies.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <!--spring-cloud-alibaba-dependencies-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-dependencies</artifactId>
            <version>${spring-cloud-alibaba-dependencies.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

例如

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-config</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    ...
</dependencies>
```

## spring cloud、spring-cloud-alibaba 区别

|                    | spring cloud 组件 | 详细  | spring-cloud-alibaba 组件  | 详细  |  推荐  |
| ----------------- | ---------- | ---------- | ---------- | ---------- | ---------- | 
| 注册中心           | Eureka    | 2.0 孵化失败 | nacos  | 性能好，感知更快  | 推荐nacos |
| 消息中间件         | 无  | 第三方替代方案：rabbitmq  | RecketMQ  |   |  |
| 分布式事务解决方案  | 无  |  第三方替代方案：2pc | Seata  |   |  |
| 分布式调度服务	    | 无  |  第三方替代方案：xxl-job            | Alibaba Cloud SchedulerX  |   |  |
| 短信平台           | 无  |               | Alibaba Cloud  SMS  |   |  |
| 分布式配置中心      | SpringCloud-Config  | 搭建复杂，无可视化配置页面   | nacos   |   |  |
| 熔断降级           | Hystrix  |   进入维护状态      | 	Sentinel  | 可视化配置，上手更简单  |  |


