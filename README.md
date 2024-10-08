# springcloud-test-root

> 官方文档

* [Spring Boot 官网](https://spring.io/projects/spring-boot)
* [Spring Cloud 官网](https://spring.io/projects/spring-cloud)
* [Spring Cloud Alibaba 官网](https://spring.io/projects/spring-cloud-alibaba)

## spring-cloud 全家桶(组件)示例

> 以下是已经完成的示例模块

- [cloud-alibaba 全家桶](./cloud-alibaba)
    - [alibaba-alicloud-oss 对象存储](./cloud-alibaba/alibaba-alicloud-oss)
    - [alibaba-alicloud-sms 短信服务](./cloud-alibaba/alibaba-alicloud-sms)
    - [alibaba-nacos-config 配置管理](./cloud-alibaba/alibaba-nacos-config)
    - [alibaba-nacos-discovery 服务管理](./cloud-alibaba/alibaba-nacos-discovery)
    - [alibaba-sentinel 限流降级](./cloud-alibaba/alibaba-sentinel)
    - [alibaba-sentinel-gateway-gateway 网关管理](./cloud-alibaba/alibaba-sentinel-gateway-gateway)
    - [alibaba-sentinel-gateway-zuul 网关管理](./cloud-alibaba/alibaba-sentinel-gateway-zuul)
    - [alibaba-sentinel-nacos 持久化配置](./cloud-alibaba/alibaba-sentinel-nacos)
    - [alibaba-sentinel-openfeign 限流和降级示例](./cloud-alibaba/alibaba-sentinel-openfeign)
    - [alibaba-stream-rocketmq 消息中间件](./cloud-alibaba/alibaba-stream-rocketmq)
- [cloud-config 配置管理](./cloud-config)
- [cloud-consul 配置和服务管理](./cloud-consul)
- [cloud-dubbo  RPC服务调用](./cloud-dubbo)
    - [cloud-dubbo2x](./cloud-dubbo/cloud-dubbo2x)
        - [cloud-dubbo2x-na-consumer](./cloud-dubbo/cloud-dubbo2x/cloud-dubbo2x-na-consumer)
        - [cloud-dubbo2x-na-provider](./cloud-dubbo/cloud-dubbo2x/cloud-dubbo2x-na-provider)
        - [cloud-dubbo2x-nacos-consumer](./cloud-dubbo/cloud-dubbo2x/cloud-dubbo2x-nacos-consumer)
        - [cloud-dubbo2x-nacos-provider](./cloud-dubbo/cloud-dubbo2x/cloud-dubbo2x-nacos-provider)
        - [cloud-dubbo2x-zookeeper-consumer](./cloud-dubbo/cloud-dubbo2x/cloud-dubbo2x-zookeeper-consumer)
        - [cloud-dubbo2x-zookeeper-provider](./cloud-dubbo/cloud-dubbo2x/cloud-dubbo2x-zookeeper-provider)
    - [cloud-dubbo3x](./cloud-dubbo/cloud-dubbo3x)
- [cloud-eureka 服务管理](./cloud-eureka)
    - [cloud-eureka-cluster 集群服务管理](./cloud-eureka/cloud-eureka-cluster)
    - [cloud-eureka-server 单节点服务管理](./cloud-eureka/cloud-eureka-server)
- [cloud-gateway API网关](./cloud-gateway)
- [cloud-nacos 配置和服务管理](./cloud-nacos)
- [cloud-openfeign REST远程调用](./cloud-openfeign)
    - [cloud-openfeign-eureka](./cloud-openfeign/cloud-openfeign-eureka)
    - [cloud-openfeign-nacos](./cloud-openfeign/cloud-openfeign-nacos)
    - [cloud-openfeign-rest](./cloud-openfeign/cloud-openfeign-rest)
    - [cloud-openfeign-zookeeper](./cloud-openfeign/cloud-openfeign-zookeeper)
- [cloud-stream 消息中间件](./cloud-stream)
- [cloud-task 短运行期微服务的任务](./cloud-task)
- [cloud-zookeeper 配置和服务管理](./cloud-zookeeper)
- [cloud-zuul API网关](./cloud-zuul)
- [remote-web-19000 REST服务提供者](./remote-web-19000)

## 本地运行环境搭建

> 以下是你必须要安装的基础软件,可以使项目正常打包及运行.

|       | 官网文档                                                                              | github | 使用版本下载                                                                       | 详细 | 是否必须安装 |
|-------|-----------------------------------------------------------------------------------|--------|------------------------------------------------------------------------------|----|--------| 
| java  | [www.oracle.com/java8](https://www.oracle.com/java/technologies/downloads/#java8) |        | [java8 downloads](https://www.oracle.com/java/technologies/downloads/#java8) |    | **必须** |
| maven | [maven.apache.org](https://maven.apache.org/)                                     |        | [maven3.6.2 downloads](https://maven.apache.org/download.cgi)                |    | **必须** |

## 适配的中间件版本

> 以下是你可能会用到的中间件

|           | 官网文档                                                              | github                                                             | 使用版本下载                                                                                                                          | 详细         | 推荐 |
|-----------|-------------------------------------------------------------------|--------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|------------|----| 
| nacos     | [nacos.io/zh-cn](https://nacos.io/zh-cn/)                         | [github.com/alibaba/nacos](https://github.com/alibaba/nacos)       | [nacos-1.4.2](https://github.com/alibaba/nacos/releases/tag/1.4.2)                                                              |            |    |
| nacos     | [nacos.io/zh-cn](https://nacos.io/zh-cn/)                         | [github.com/alibaba/nacos](https://github.com/alibaba/nacos)       | [nacos-2.1.0](https://github.com/alibaba/nacos/releases/tag/2.1.0)                                                              | 搭配dubbo3.x |    |
| zookeeper | [zookeeper.apache.org](http://zookeeper.apache.org/releases.html) |                                                                    | [zookeeper-3.6.3-bin.tar.gz](https://www.apache.org/dyn/closer.lua/zookeeper/zookeeper-3.6.3/apache-zookeeper-3.6.3-bin.tar.gz) |            |    |
| Sentinel  | [Sentinel-github](https://github.com/alibaba/Sentinel)            | [github.com/alibaba/Sentinel](https://github.com/alibaba/Sentinel) | [sentinel-dashboard-1.8.2.jar](https://github.com/alibaba/Sentinel/releases)                                                    |            |    |
| rabbitmq  | [www.rabbitmq.com](https://www.rabbitmq.com/)                     | [github.com/rabbitmq](https://github.com/rabbitmq)                 | [rabbitmq-3.9.8](https://github.com/rabbitmq/rabbitmq-server/releases/download/v3.9.8)                                          |            |    |
| kafka     | [kafka.apache.org](http://kafka.apache.org/)                      |                                                                    | [kafka_2.13-3.0.0.tgz](http://kafka.apache.org/downloads)                                                                       |            |    |
| rocketmq  | [rocketmq.apache.org](http://rocketmq.apache.org/)                | [github.com/apache/rocketmq](https://github.com/apache/rocketmq)   | [rocketmq-4.9.1](https://www.apache.org/dyn/closer.cgi?path=rocketmq/4.9.1/rocketmq-all-4.9.1-source-release.zip)               |            |    |
| Git       | [git-scm.com](https://git-scm.com/)                               |                                                                    | [git-latest](https://git-scm.com/downloads)                                                                                     |            |    |
| consul    | [www.consul.io](https://www.consul.io/)                           |                                                                    | [downloads-consul-1.11.1](https://www.consul.io/downloads)                                                                      |            |    |


## 后续计划

> 以下是后续计划预研的技术

|              | 说明                            | 是否完成 | 
|--------------|-------------------------------|------|
| cloud-stream | 预研消息中间件kafka、rabbit、rocketmq等 | 计划中  |


## springboot、spring-cloud、spring-cloud-alibaba 版本对应

- 参考地址：https://spring.io/projects/spring-cloud#overview

**Release Train**

> spring-cloud、springboot、spring-cloud-alibaba 版本对应

| spring-cloud Release Train                                                                                          | spring-boot Version                   | spring-cloud-alibaba  Version |
|:--------------------------------------------------------------------------------------------------------------------|:--------------------------------------|:------------------------------|
| [2022.0.x](https://github.com/spring-cloud/spring-cloud-release/wiki/Spring-Cloud-2022.0-Release-Notes) aka Kilburn | 3.0.x, 3.1.x (Starting with 2022.0.3) | 2021.x                        |
| [2021.0.x](https://github.com/spring-cloud/spring-cloud-release/wiki/Spring-Cloud-2021.0-Release-Notes) aka Jubilee | 2.6.x, 2.7.x (Starting with 2021.0.3) | 2021.x                        |
| [2020.0.x](https://github.com/spring-cloud/spring-cloud-release/wiki/Spring-Cloud-2020.0-Release-Notes) aka Ilford  | 2.4.x, 2.5.x (Starting with 2020.0.3) | 2020.x                        |
| [Hoxton](https://github.com/spring-cloud/spring-cloud-release/wiki/Spring-Cloud-Hoxton-Release-Notes)               | 2.2.x, 2.3.x (Starting with SR5)      | 2.2.x                         |
| [Greenwich](https://github.com/spring-projects/spring-cloud/wiki/Spring-Cloud-Greenwich-Release-Notes)              | 2.1.x                                 | 2.1.x                         |  
| [Finchley](https://github.com/spring-projects/spring-cloud/wiki/Spring-Cloud-Finchley-Release-Notes)                | 2.0.x                                 | 2.0.x                         |
| [Edgware](https://github.com/spring-projects/spring-cloud/wiki/Spring-Cloud-Edgware-Release-Notes)                  | 1.5.x                                 | 1.5.x                         |
| [Dalston](https://github.com/spring-projects/spring-cloud/wiki/Spring-Cloud-Dalston-Release-Notes)                  | 1.5.x                                 | 1.5.x                         |

> Spring Cloud Dalston, Edgware, Finchley, 和 Greenwich 都已达到生命周期终止状态，不再受支持.

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

## spring-cloud、spring-cloud-alibaba 区别

|           | spring cloud 组件    | 详细               | spring-cloud-alibaba 组件  | 详细          | 推荐      |
|-----------|--------------------|------------------|--------------------------|-------------|---------| 
| 注册中心      | Eureka             | 2.0 孵化失败         | nacos                    | 性能好，感知更快    | 推荐nacos |
| 消息中间件     | 无                  | 第三方替代方案：rabbitmq | RecketMQ                 |             |         |
| 分布式事务解决方案 | 无                  | 第三方替代方案：2pc      | Seata                    |             |         |
| 分布式调度服务	  | 无                  | 第三方替代方案：xxl-job  | Alibaba Cloud SchedulerX |             |         |
| 短信平台      | 无                  |                  | Alibaba Cloud  SMS       |             |         |
| 分布式配置中心   | SpringCloud-Config | 搭建复杂，无可视化配置页面    | nacos                    |             |         |
| 熔断降级      | Hystrix            | 进入维护状态           | 	Sentinel                | 可视化配置，上手更简单 |         |

总结：若项目中使用了**spring-cloud-alibaba 组件**，那么，推荐项目上，所有组统一使用**spring-cloud-alibaba 组件**.

## 你还可以学习其他项目

> 以下是你可能需要学习的其他项目及技术

|                                          | 资源地址                                                                                         | 说明                                                                                           |  |
|------------------------------------------|----------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------|--|
| github/zhengjiaao                        | [github.com/zhengjiaao](https://github.com/zhengjiaao)                                       | 主页面，展示一些比较重要技术预研项目                                                                           |  |
| zhengjiaao/springcloud-test-root         | [springcloud-test-root](https://github.com/zhengjiaao/springcloud-test-root)                 | springcloud 全家桶(组件) 技术预研框架,内容较多，较基础，偏向于技术的应用，适合初学者快速掌握某项技术，欢迎Star，推荐学习                       |  |
| zhengjiaao/spring-boot-starter-test-root | [spring-boot-starter-test-root](https://github.com/zhengjiaao/spring-boot-starter-test-root) | spring-boot-starter 2.x 全家桶(组件) 技术预研框架,内容较多，较基础，偏向于技术的应用，适合初学者快速掌握某项技术，欢迎Star，推荐学习           |  |
| zhengjiaao/springboot-test-root          | [springboot-test-root](https://github.com/zhengjiaao/springboot-test-root)                   | springboot 2.x 技术预研框架,内容较多，较基础，偏向于技术的应用，适合初学者快速掌握某项技术，欢迎Star，推荐学习                            |  |
| zhengjiaao/spring5x                      | [spring5x](https://github.com/zhengjiaao/spring5x)                                           | spring 5.x 技术预研框架                                                                            |  |
| zhengjiaao/springboot-test-redis         | [springboot-test-redis](https://github.com/zhengjiaao/springboot-test-redis)                 | springboot 2.x + redis 项目实战-实例,很早之前学习redis写的，可以学习redis工具类、数据缓存、消息发布和订阅等                      |  |
| zhengjiaao/springboot-test-mybatis-root  | [springboot-test-mybatis-root](https://github.com/zhengjiaao/springboot-test-mybatis-root)   | springboot 2.x 集成 mybatis、mybatis-plus、分页插件 pagehelper。 使用 mybatis 实现简单的CRUD操作，动态插入、批量插入等操作。 |  |