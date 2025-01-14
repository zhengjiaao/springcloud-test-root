# cloud-stream

**不推荐使用cloud-stream，会增加上手难度，文档不完善，集成不完美，推荐使用各个中间件官网提供的JAVA-SDK。**

> 官方参考文档

- [spring-cloud-stream](https://spring.io/projects/spring-cloud-stream-applications#overview)

- [rabbitmq.com](https://www.rabbitmq.com/getstarted.html)
- [spring-cloud-stream-binder-rabbit 3.1.4](https://docs.spring.io/spring-cloud-stream/docs/3.1.4/reference/html/spring-cloud-stream.html#_binding_and_binding_names)

- [rocketmq.apache.org](http://rocketmq.apache.org/)
- [RocketMQ_Example.md](https://github.com/apache/rocketmq/blob/master/docs/cn/RocketMQ_Example.md)

- [kafka.apache.org](http://kafka.apache.org/)

## spring-cloud-stream 有什么作用？

问题：

```text
假设公司业务项目用了RabbitMQ，而大数据项目用了Kafka。
出现有两个消息框架，相对于程序员来说其实并不友好，还得两个都掌握，并且两个维护起来也不好维护。
```

方案：

```text
RabbitMQ和Kafka是两个不同的框架，两个消息模型上也存在着差异，并且代码上用法也不一样。
Spring Cloud Stream就是不再关注具体MQ的细节，可以在不改代码的基础上，来完成Rabbit和Kafka两个不同的消息中间件的切换。
```

总结成一句话：**屏蔽底层消息中间件的差异，降低切换成本，统一消息的编程模型.**

## spring-cloud-stream 支持哪些消息中间件？

> 支持的 消息中间件

更多查看官网：https://spring.io/projects/spring-cloud-stream-applications#overview

- RabbitMQ
- Apache Kafka
- RocketMQ
- ... ...

## spring-cloud-stream 版本对应

> 版本选型 spring-cloud-stream、Spring Cloud、Spring Boot

| spring-cloud-stream | Spring Cloud         | Spring Boot                                           | 
|---------------------|----------------------|-------------------------------------------------------|
| 4.0.x               | 2022.0.x aka Kilburn | 3.0.x                                                 |
| 3.2.x               | 2021.0.x aka Jubilee | 2.6.x, 2.7.x (Starting with 2021.0.3 of Spring Cloud) |
| 3.1.x               | 2020.0.x aka Ilford  | 2.4.x, 2.5.x (Starting with 2020.0.3 of Spring Cloud) |








