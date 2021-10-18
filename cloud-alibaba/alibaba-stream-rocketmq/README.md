
# Alibaba RocketMQ

- [Spring Cloud Alibaba RocketMQ Binder](https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/en-us/index.html#_spring_cloud_alibaba_rocketmq_binder)

- [快速开始](https://rocketmq.apache.org/docs/quick-start/)

- [下载二进制 rocketmq-all-4.3.2-bin-release.zip](https://www.apache.org/dyn/closer.cgi?path=rocketmq/4.3.2/rocketmq-all-4.3.2-bin-release.zip)

- [基于rocketmq-spring](https://github.com/apache/rocketmq-spring)

## 特征

-  同步发送消息
-  异步发送消息
-  以单向模式发送消息
-  发送有序消息
-  发送批量消息
-  发送交易消息
-  发送具有延迟级别的预定消息
-  以并发模式（广播/集群）消费消息
-  消费有序消息
-  使用标记或 sql92 表达式过滤消息
-  支持消息追踪
-  支持认证和授权
-  支持请求-回复消息交换模式
-  使用推/拉模式消费消息

## 先决条件
- JDK 1.8 及以上
- Maven 3.0 及以上
- Spring Boot 2.0 及以上

## 用法
使用 maven 添加依赖项：

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-stream-rocketmq</artifactId>
</dependency>

<!--或者-->
<!--<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-stream-binder-rocketmq</artifactId>
</dependency>-->
```

## 示例
Please see the [rocketmq-spring-boot-samples](https://github.com/apache/rocketmq-spring/blob/master/rocketmq-spring-boot-samples).

## 用户指南
Please see the [wiki](https://github.com/apache/rocketmq-spring/wiki) page.
