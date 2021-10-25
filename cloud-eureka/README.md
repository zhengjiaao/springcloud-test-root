# cloud-eureka  服务注册和发现     

- [eureka-server](cloud-eureka-server/src/main/java/com/zja) 启动 Eureka服务
- [eureka-service](cloud-eureka-service/src/main/java/com/zja) 服务注册者
- [eureka-web](cloud-eureka-web/src/main/java/com/zja) 服务消费者


> 主 pom.xml     
```xml
    <modules>
        <module>cloud-eureka-server</module>
        <module>cloud-eureka-service</module>
        <module>cloud-eureka-web</module>
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

## cloud-eureka-server 服务注册中心     
> pom.xml   
```xml
    <!--cloud-eureka-server 注册中心(相当于zookerper)-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
```
application.yml     
```yaml
server:
  port: 16000

spring:
  application:
    name: eureka-server

# eureka-server 注册中心服务端地址
eureka:
  client:
    registerWithEureka: false  # 默认true，关闭自我注册，默认情况下，服务中心也会作为客户端来自己注册自己
    fetchRegistry: false       # 默认true，由于注册中心的职责就是维护服务实例，它并不需要去检索服务，不需要立即向eureka服务注册
    serviceUrl:
      defaultZone: http://localhost:${server.port}/eureka/   # eureka-server 注册中心服务端地址
```
java相关代码    
```java
@SpringBootApplication
@EnableEurekaServer //注解启动一个服务注册中心提供给其他应用进行对话
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

## eureka-service 服务提供者     
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
  port: 18001   #端口号

spring:
  application:
    name: eureka-service  # 服务名称

eureka:
  client:
    service-url:
      defaultZone: http://localhost:16000/eureka/  # 服务注册中心的地址
```
java相关代码    
```java
@RequestMapping
@RestController("ServiceHelloController")
public class HelloController {
    @GetMapping("v1/hello")
    public String get(@RequestParam String name) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "name: " + name;
    }
}

@SpringBootApplication
@EnableDiscoveryClient  //注解开启应用注册服务
public class EurekaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceApplication.class, args);
    }
}
```

## eureka-web  服务消费者         
> pom.xml   
```xml
        <!-- ********** springcloud 相关依赖 Strat *********** -->
        <!-- springcloud eureka-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!--springcloud openfign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!-- ********** springcloud 相关依赖 End *********** -->
```
application.yml     
```yaml
server:
  port: 18002   #端口号

spring:
  application:
    name: eureka-client   # 服务名称

eureka:
  client:
    service-url:
      registerWithEureka: false # 默认true，关闭自我注册，默认情况下，服务中心也会作为客户端来自己注册自己
      defaultZone: http://localhost:16000/eureka/  # 服务注册中心的地址

# OpenFeign (可选配置,都不是必须的，默认就行)
feign:
  client:
    config:
      default:
        connectTimeout: 5000  # 指定Feign客户端连接提供者的超时时限   取决于网络环境
        readTimeout: 5000     # 指定Feign客户端从请求到获取到提供者给出的响应的超时时限  取决于业务逻辑运算时间
  compression:
    request:
      enabled: true  # 是否对请求进行GZIP压缩
      mime-types: text/xml,application/xml,application/json  # 指定压缩的请求数据类型
      min-request-size: 2048  # 超过该大小的请求会被压缩
    response:
      enabled: true  # 是否对响应进行GZIP压缩
  hystrix:
    enabled: false   # 在Feign中开启Hystrix，默认false关闭，要使用fallback功能这里必须启用
# OpenFeign 特定类指定特定日志级别：
logging:
  level:
    com.zja.client.ServiceOpenFeign: debug   # 修改日志级别 NONE、BASIC、HEADERS、FULL

#  NONE，无记录（DEFAULT）。
#  BASIC，只记录请求方法和URL以及响应状态代码和执行时间。
#  HEADERS，记录基本信息以及请求和响应标头。
#  FULL，记录请求和响应的头文件，正文和元数据。 推荐使用
```
java相关代码    
```java
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}

@FeignClient(name = "EUREKA-SERVICE") //要调用的服务器名称
public interface ServiceOpenFeign {
    @GetMapping("/v1/hello")
    String hello(@RequestParam("name") String name);
}

@RequestMapping
@RestController
public class HelloController {
    @Autowired
    private ServiceOpenFeign serviceOpenFeign;
    @GetMapping("hello")
    public String gethello(@RequestParam String name) {
        String hello = serviceOpenFeign.hello(name);
        System.out.println("OpenFeign响应结果: "+hello);
        return hello;
    }
}

@SpringBootApplication
//@EnableEurekaClient  //不推荐，默认没有自我注册功能
@EnableDiscoveryClient //建议使用，默认带有自我注册功能
@EnableFeignClients // 开启 OpenFeign
public class EurekaWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaWebApplication.class,args);
    }
}
```

访问：http://localhost:18002/hello?name=张三     
访问结果：   
OpenFeign响应结果: 张三   
