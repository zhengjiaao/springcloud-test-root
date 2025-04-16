# cloud-commons 

> 官方文档  
> spring-cloud-commons 抽象 解析

- [spring-cloud-commons/docs](https://docs.spring.io/spring-cloud-commons/docs/current/reference/html/)

### 一、Spring Cloud Commons 核心功能

| 功能模块         | 核心能力                                                     |
| :--------------- | :----------------------------------------------------------- |
| **服务发现抽象** | 提供 `DiscoveryClient` 统一接口，支持Eureka、Zookeeper、Consul等注册中心 |
| **负载均衡**     | 通过 `LoadBalancerClient` 和 `@LoadBalanced` 实现客户端负载均衡 |
| **服务调用**     | 标准化RestTemplate/WebClient的服务间调用                     |
| **断路器抽象**   | 定义 `CircuitBreaker` SPI，支持Resilience4j、Hystrix等实现   |
| **配置抽象**     | 统一的 `PropertySource` 管理，支持动态刷新                   |
| **事件总线**     | 通过 `Spring Cloud Bus` 实现分布式事件传播                   |
| **连接池管理**   | 通用连接池配置（如Feign的HTTP连接池）                        |

- DiscoveryClient 接口 服务发现
- ServiceRegistry 接口 服务注册

- ServiceInstanceChooser 接口 客户端负载均衡
- EnableCircuitBreaker 接口 断路器功能

------

### 二、典型应用场景

1. **微服务注册与发现**
    - 服务自动注册到注册中心，消费者动态发现服务实例
    - **技术组合**：`DiscoveryClient` + `LoadBalancerClient`
2. **跨服务容错调用**
    - 服务A调用服务B时自动处理超时、熔断、降级
    - **技术组合**：`@CircuitBreaker` + `FallbackFactory`
3. **配置中心集成**
    - 所有服务从统一配置中心获取配置，支持动态刷新
    - **技术组合**：`@RefreshScope` + Config Server
4. **分布式事件通知**
    - 通过消息总线广播配置变更事件
    - **技术组合**：Spring Cloud Bus + RabbitMQ/Kafka