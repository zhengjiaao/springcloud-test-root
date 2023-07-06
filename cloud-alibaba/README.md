# cloud-alibaba

> 官方参考文档


## spring-cloud-alibaba 全家桶(组件)示例

> 以下是已经完成的示例模块

- [cloud-alibaba 全家桶]()
    - [alibaba-alicloud-oss 对象存储](./alibaba-alicloud-oss)
    - [alibaba-alicloud-sms 短信服务](./alibaba-alicloud-sms)
    - [alibaba-nacos-config 配置管理](./alibaba-nacos-config)
    - [alibaba-nacos-discovery 服务管理](./alibaba-nacos-discovery)
    - [alibaba-sentinel 限流降级](./alibaba-sentinel)
    - [alibaba-sentinel-gateway-gateway 网关管理](./alibaba-sentinel-gateway-gateway)
    - [alibaba-sentinel-gateway-zuul 网关管理](./alibaba-sentinel-gateway-zuul)
    - [alibaba-sentinel-nacos 持久化配置](./alibaba-sentinel-nacos)
    - [alibaba-sentinel-openfeign 限流和降级示例](./alibaba-sentinel-openfeign)
    - [alibaba-stream-rocketmq 消息中间件](./alibaba-stream-rocketmq)


## spring-cloud、spring-cloud-alibaba 区别

|                    | spring cloud 组件 | 详细  | spring-cloud-alibaba 组件  | 详细  |  推荐  |
| ----------------- | ---------- | ---------- | ---------- | ---------- | ---------- | 
| 注册中心           | Eureka    | 2.0 孵化失败 | nacos  | 性能好，感知更快  | 推荐nacos |
| 消息中间件         | 无  | 第三方替代方案：rabbitmq  | RecketMQ  |   |  |
| 分布式事务解决方案  | 无  |  第三方替代方案：2pc | Seata  |   |  |
| 分布式调度服务	    | 无  |  第三方替代方案：xxl-job            | Alibaba Cloud SchedulerX  |   |  |
| 短信平台           | 无  |               | Alibaba Cloud  SMS  |   |  |
| 分布式配置中心      | SpringCloud-Config  | 搭建复杂，无可视化配置页面   | nacos   |   |  |
| 熔断降级           | Hystrix  |   进入维护状态      | 	Sentinel  | 可视化配置，上手更简单  |  |

总结：若项目中使用了**spring-cloud-alibaba 组件**，那么，推荐项目上，所有组统一使用**spring-cloud-alibaba 组件**.

## 适配的中间件版本

> 以下是你可能会用到的中间件

|                    | 官网文档 | github  | 使用版本下载  | 详细  |  推荐  |
| ----------------- | ---------- | ---------- | ---------- | ---------- | ---------- | 
| nacos            | [nacos.io/zh-cn](https://nacos.io/zh-cn/)          | [github.com/alibaba/nacos](https://github.com/alibaba/nacos) | [nacos-1.4.2](https://github.com/alibaba/nacos/releases/tag/1.4.2)  |   |  |
| nacos            | [nacos.io/zh-cn](https://nacos.io/zh-cn/)          | [github.com/alibaba/nacos](https://github.com/alibaba/nacos) | [nacos-2.1.0](https://github.com/alibaba/nacos/releases/tag/2.1.0)  | 搭配dubbo3.x  |  |
| Sentinel         | [Sentinel-github](https://github.com/alibaba/Sentinel) | [github.com/alibaba/Sentinel](https://github.com/alibaba/Sentinel) | [sentinel-dashboard-1.8.2.jar](https://github.com/alibaba/Sentinel/releases)  |   |  |
| rocketmq         | [rocketmq.apache.org](http://rocketmq.apache.org/) | [github.com/apache/rocketmq](https://github.com/apache/rocketmq) | [rocketmq-4.9.1](https://www.apache.org/dyn/closer.cgi?path=rocketmq/4.9.1/rocketmq-all-4.9.1-source-release.zip)  |   |  |

## 本地运行环境搭建

> 以下是你必须要安装的基础软件,可以使此项目可以正常打包及运行.

|       | 官网文档                                                                              | github  | 使用版本下载                                                                       | 详细  | 是否必须安装 |
|-------|-----------------------------------------------------------------------------------| ---------- |------------------------------------------------------------------------------| ---------- |--------| 
| java  | [www.oracle.com/java8](https://www.oracle.com/java/technologies/downloads/#java8) |  | [java8 downloads](https://www.oracle.com/java/technologies/downloads/#java8) |   | **必须** |
| maven | [maven.apache.org](https://maven.apache.org/)                                     |  | [maven3.6.2 downloads](https://maven.apache.org/download.cgi)                |   | **必须** |





