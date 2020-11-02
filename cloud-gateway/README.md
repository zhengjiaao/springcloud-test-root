# cloud-gateway  API网关      
> 主 pom.xml       
```xml
    <modules>
        <module>gateway</module>
        <module>gateway-nacos-server</module>
        <module>gateway-web-a</module>
        <module>gateway-web-b</module>
    </modules>

    <dependencyManagement>
        <dependencies>
            <!--spring-cloud-下载仓库地址-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

## gateway
> pom.xml   
```xml
    <!--springboot web 与gateway 不兼容,换成webflux-->
    <!--<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>

    <!--springcloud gateway-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway</artifactId>
    </dependency>

    <!-- springcloud eureka-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>

    <!--springboot devtools 热部署-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <optional>true</optional>
    </dependency>
```
application.yml     
```yaml
server:
  port: 8084
spring:
  application:
    name: gateway-web
  #redis配置
#  redis:
#    host: 192.168.159.128  # 虚拟机 docker redis
#    port: 6379
#    database: 0
  #开启热部署
  devtools:
    restart:
      enabled: true
  #网关配置
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true
      # 网关静态路由配置
      routes:
        - id: gateway_route
          uri: http://127.0.0.1:8082
          order: 1
          predicates: # 断言，当路由满足全部断言时进行转发
            - Path=/web/**
          filters:  # 过滤器
            - StripPrefix=1 # 删除一级路径
#          filters:
#            #- RewritePath=/(?<segment>.*),/$\{segment}  # 拦截器
#            - name: RequestRateLimiter
#              args:
#                key-resolver: '#{@uriKeyResolver}'
#                redis-rate-limiter.replenishRate: 1  # 每秒钟向redis注册一个令牌
#                redis-rete-limiter.burstCapacity: 2  # 每秒最多访问2次
#      discovery:  # 发现服务-目的实现动态路由配置
#        locator:
#          enabled: true

# 动态路由配置
# 设置注册中心地址
#eureka:
#  client:
#    service-url:
#      defaultZone: http://localhost:8080/eureka/  # 服务注册中心的地址


# 日志打印级别
logging:
  level:
    com.zja.service.feign.HelloWorldService: debug   # OpenFeign 给特定类指定日志级别
    org.apache.zookeeper.ClientCnxn: WARN    # zookeeper 注册中心日志级别

#  NONE，无记录（DEFAULT）。
#  BASIC，只记录请求方法和URL以及响应状态代码和执行时间。
#  HEADERS，记录基本信息以及请求和响应标头。
#  FULL，记录请求和响应的头文件，正文和元数据。 推荐使用
```
java 相关代码   
```java
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
```

## gateway-web-a
> pom.xml   
```xml
    <!-- springcloud eureka-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
```
application.yml     
```yaml
server:
  port: 8082
spring:
  application:
    name: web

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8080/eureka/  # 服务注册中心的地址
```
java 相关代码   
```java
@RestController
public class HelloWorldController {
    @GetMapping("v1/hello")
    public String hello() {
        return "hello-a";
    }
}

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayWebAApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayWebAApplication.class, args);
    }
}
```

## gateway-web-b
> pom.xml   
```xml
    <!-- springcloud eureka-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
```
application.yml     
```yaml
server:
  port: 8082
spring:
  application:
    name: web

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8080/eureka/  # 服务注册中心的地址
```
java 相关代码   
```java
@RestController
public class HelloWorldController {
    @GetMapping("v1/hello")
    public String hello() {
        return "hello-b";
    }
}

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayWebBApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayWebBApplication.class, args);
    }
}
```

访问：     
localhost:18000/v1/hello    
访问结果：类似负载均衡效果   
```json
hello-b
hello-a
...
```    