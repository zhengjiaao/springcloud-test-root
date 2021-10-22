# cloud-zookeeper  服务注册和发现    

> 由于升级框架版本，springboot-->2.5.5 、springcloud-->2020.0.4 以上，所以建议zookeeper服务端版本下载安装3.5.x [zookeeper.apache.org](https://zookeeper.apache.org/releases.html)以上

cloud-zookeeper 
> 主pom.xml  
```xml
    <modules>
        <module>zookeeper-openfeign-hystix-consumer</module>
        <module>zookeeper-openfeign-hystix-provider</module>
        <module>zookeeper-service</module>
        <module>zookeeper-web</module>
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

## zookeeper-service    
> pom.xml   
```xml
    <!--spring cloud zookeeper：springcloud使用zk做服务注册发现用的-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
    </dependency>

    <!--zk工具包：连接zk服务的客户端包-->
    <dependency>
        <groupId>org.apache.zookeeper</groupId>
        <artifactId>zookeeper</artifactId>
        <version>3.4.9</version>
        <exclusions>
            <exclusion>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-log4j12</artifactId>
            </exclusion>
            <exclusion>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
```
application.yml     
```yaml
server:
  port: 18003

spring:
  application:
    name: zookeeper-service
  cloud:
    zookeeper:
      connect-string: localhost:2181
      discovery:
        enabled: true  # 关闭服务发现，否则启动会报异常

logging:
  level:
    org.apache.zookeeper.ClientCnxn: WARN
```
java 相关代码   
```java
@RestController
public class HelloWorldController {
    @GetMapping("v1/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("v1/helloworld")
    public String helloworld() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello World!";
    }
}

@SpringBootApplication
@EnableDiscoveryClient //向注册中心(zookeeper)上注册服务
public class ZookeeperServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperServiceApplication.class, args);
    }
}
```

## zookeeper-web    
> pom.xml   
```xml
    <!--spring cloud zookeeper：springcloud使用zk做服务注册发现用的-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
    </dependency>

    <!--zk工具包：连接zk服务的客户端包-->
    <dependency>
        <groupId>org.apache.zookeeper</groupId>
        <artifactId>zookeeper</artifactId>
        <version>3.4.9</version>
        <exclusions>
            <exclusion>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-log4j12</artifactId>
            </exclusion>
            <exclusion>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
            </exclusion>
        </exclusions>
    </dependency>

    <!--spring cloud openfeign-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    </dependency>
```
application.yml     
```yaml
server:
  port: 18004
spring:
  application:
    name: zookeeper-web
  cloud:
    zookeeper:
      connect-string: localhost:2181
      discovery:
        enabled: true

# OpenFeign (可选配置,都不是必须的，默认就行)
feign:
  httpclient:
    enabled: false # feign 默认使用 httpclint
  okhttp:
    enabled: true  # feign可以用okhttp代替传统的httpclient，性能更好
  client:
    config:
      default:
        connectTimeout: 5000  # 指定Feign客户端连接提供者的超时时限   取决于网络环境
        readTimeout: 5000     # 指定Feign客户端从请求到获取到提供者给出的响应的超时时限  取决于业务逻辑运算时间
#        loggerLevel: FULL    # 全局默认请求打印日志级别：NONE、BASIC、HEADERS、FULL
  compression:
    request:
      enabled: true  # 是否对请求进行GZIP压缩
      mime-types: text/xml,application/xml,application/json  # 指定压缩的请求数据类型
      min-request-size: 2048  # 超过该大小的请求会被压缩
    response:
      enabled: true  # 是否对响应进行GZIP压缩
  hystrix:
    enabled: true   # 在Feign中开启Hystrix，默认false关闭，要使用fallback功能这里必须启用

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
@Configuration
public class FeignClientConfig {

    /**
     * Feign 默认重试策略：
     *  period=100 发起当前请求的时间间隔，单位毫秒
     *  maxPeriod=1000 发起当前请求的最大时间间隔，单位毫秒
     *  maxAttempts=5 最多请求次数，包括第一次
     */
    @Bean
    public Retryer feignRetryer() {
        //默认：100 1 5
        return new Retryer.Default(100, SECONDS.toMillis(1), 1);
    }
}

@Component
// 服务器降级：熔断-服务宕机处理
//@FeignClient(name = "springcloud-zookeeper-service", configuration = FeignClientConfig.class,fallback = HelloWorldServiceFallback.class)
@FeignClient(name = "zookeeper-service", fallback = HelloWorldServiceFallback.class)
public interface HelloWorldService {

    @GetMapping("v1/hello")
    String hello();

    @GetMapping("v1/helloworld")
    String helloworld();
}

@Component
public class HelloWorldServiceFallback implements HelloWorldService {
    @Override
    public String hello() {
        return "服务端没有启动！";
    }
    @Override
    public String helloworld() {
        return "服务端没有启动！";
    }
}

@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class HelloWorldController {

    @Resource
    private HelloWorldService helloWorldService;

    @GetMapping("v1/hello")
    public String hello() {
        return helloWorldService.hello();
    }

    @GetMapping("v1/helloworld")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    //@HystrixCommand  //开启全局默认Fallback
    public String helloworld() {
        //int age = 10/0;  // 程序异常
        return helloWorldService.helloworld();
    }

    public String paymentTimeOutFallbackMethod() {
        return "消费者-对方系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    // 下面是全局fallback方法
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}

@SpringBootApplication
@EnableDiscoveryClient //建议使用，默认带有自我注册功能
@EnableFeignClients //(basePackages = "com.zja.service.feign") // 开启 OpenFeign
@EnableHystrix
public class ZookeeperWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperWebApplication.class, args);
    }
}
```
访问：     
localhost:18004/v1/hello    
localhost:18004/v1/v1/helloworld    
>具体结果 访问试试  

## zookeeper-openfeign-hystix-provider
> pom.xml   
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
            <!--先排除自带的zookeeper-->
            <exclusions>
                <exclusion>
                    <groupId>org.apache.zookeeper</groupId>
                    <artifactId>zookeeper</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <!--添加zookeeper3.4.9版本-->
        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
            <version>3.4.9</version>
            <exclusions>
                <exclusion>
                    <groupId>org.slf4j</groupId>
                    <artifactId>slf4j-log4j12</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>log4j</groupId>
                    <artifactId>log4j</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
```
application.yml     
```yaml
server:
  port: 18002

spring:
  application:
    name: zk-openfeign-hystix-provider
  cloud:
    zookeeper:
      connect-string: localhost:2181

logging:
  level:
    org.apache.zookeeper.ClientCnxn: WARN
```
java 相关代码   
```java
@Service
public class HystrixOpenFeignService {

    public String provider_Ok(Integer id){
        return "线程池:  " + Thread.currentThread().getName() + "  provider_Ok,id:  " + id + "\t" + "O(∩_∩)O哈哈~";
    }

    //服务降级：当多并发调用请求接口或者业务出现异常，服务承受能力不足时会触发服务降级
    @HystrixCommand(fallbackMethod = "provider_TimeOut_Handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String provider_TimeOut(Integer id){
        //int age = 10/0;  /模拟出异常
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:  " + Thread.currentThread().getName() + "provider_TimeOut,id:  " + id + "\t" + "O(∩_∩)O哈哈~" + "  耗时(秒): ";
    }

    private String provider_TimeOut_Handler(Integer id) {
        return "线程池:  " + Thread.currentThread().getName() + "  80系统繁忙或者运行报错，请稍后再试,id:  " + id + "\t" + "o(╥﹏╥)o";
    }

    /*******************服务熔断********************/
    @HystrixCommand(fallbackMethod = "providerCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数到达 10次
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期，是否尝试服务可用
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸 60%
    })
    public String providerCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    private String providerCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }
}

@RestController
@Slf4j
public class HystrixController {

    @Autowired
    HystrixOpenFeignService hystrixOpenFeignService;

    //正常请求
    @GetMapping("provider/ok/{id}")
    public String Request_Ok(@PathVariable("id") Integer id){
        return hystrixOpenFeignService.provider_Ok(id);
    }

    //请求超时
    @GetMapping("provider/timeout/{id}")
    public String Request_TimeOut(@PathVariable("id") Integer id){
        return hystrixOpenFeignService.provider_TimeOut(id);
    }

    //熔断：熔断打开-->熔断关闭--->熔断半开：当多次错误，再输入正确的值，会发现还是错误信息，当正确率高了，慢慢的熔断半开-->熔断关闭
    @GetMapping("provider/circuitbreaker/{id}")
    public String providerCircuitBreaker(@PathVariable("id") Integer id){
        return hystrixOpenFeignService.providerCircuitBreaker(id);
    }
}

@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或者zookeeper作为注册中心时注册服务
@EnableFeignClients //(basePackages = "com.zja.service.feign") // 开启 OpenFeign
@EnableHystrix  // 开启服务降级/服务熔断
public class ZkOpenfeignHystixProvider {
    public static void main(String[] args) {
        SpringApplication.run(ZkOpenfeignHystixProvider.class, args);
    }
}
```

## zookeeper-openfeign-hystix-consumer
> pom.xml   
```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
        <!--先排除自带的zookeeper-->
        <exclusions>
            <exclusion>
                <groupId>org.apache.zookeeper</groupId>
                <artifactId>zookeeper</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <!--添加zookeeper3.4.9版本-->
    <dependency>
        <groupId>org.apache.zookeeper</groupId>
        <artifactId>zookeeper</artifactId>
        <version>3.4.9</version>
        <exclusions>
            <exclusion>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-log4j12</artifactId>
            </exclusion>
            <exclusion>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
```
application.yml     
```yaml
server:
  port: 18001
spring:
  application:
    name: zk-openfeign-hystix-consumer
  cloud:
    zookeeper:
      connect-string: localhost:2181
#      discovery:
#        enabled: true
    loadbalancer:
      ribbon:
        enabled: false

# OpenFeign (可选配置,都不是必须的，默认就行)
feign:
  #  httpclient:
  #    enabled: false # feign 默认使用 httpclint
  #  okhttp:
  #    enabled: true  # feign可以用okhttp代替传统的httpclient，性能更好
  client:
    config:
      default:
        connectTimeout: 5000  # 指定Feign客户端连接提供者的超时时限   取决于网络环境
        readTimeout: 5000     # 指定Feign客户端从请求到获取到提供者给出的响应的超时时限  取决于业务逻辑运算时间
  #        loggerLevel: FULL    # 全局默认请求打印日志级别：NONE、BASIC、HEADERS、FULL
  compression:
    request:
      enabled: true  # 是否对请求进行GZIP压缩
      mime-types: text/xml,application/xml,application/json  # 指定压缩的请求数据类型
      min-request-size: 2048  # 超过该大小的请求会被压缩
    response:
      enabled: true  # 是否对响应进行GZIP压缩
  hystrix:
    enabled: true   # 在Feign中开启Hystrix，默认false关闭，要使用fallback功能这里必须启用

# 日志打印级别
logging:
  level:
    com.zja.service.feign.HelloWorldService: debug   # OpenFeign 给特定类指定日志级别
    org.apache.zookeeper.ClientCnxn: WARN    # zookeeper 注册中心日志级别
```
java 相关代码   
```java
@FeignClient(name = "zk-openfeign-hystix-provider",fallback = HystrixOpenFeignServiceFallback.class)
public interface HystrixOpenFeignService {
    @GetMapping("provider/ok/{id}")
    String provider_Ok(@PathVariable("id") Integer id);

    @GetMapping("provider/timeout/{id}")
    String provider_TimeOut(@PathVariable("id") Integer id);
}

@Component
public class HystrixOpenFeignServiceFallback implements HystrixOpenFeignService {

    @Override
    public String provider_Ok(Integer id) {
        return "-----HystrixOpenFeignServiceFallback fall provider_Ok ,o(╥﹏╥)o";
    }

    @Override
    public String provider_TimeOut(Integer id) {
        return "-----HystrixOpenFeignServiceFallback fall provider_TimeOut ,o(╥﹏╥)o";
    }
}

@RestController
@DefaultProperties(defaultFallback = "consumer_Global_FallbackMethod")
public class HystrixController {

    @Resource
    HystrixOpenFeignService hystrixOpenFeignService;

    //正常请求
    @GetMapping("consumer/ok/{id}")
    public Object consumer_Ok(@PathVariable("id") Integer id){
        return hystrixOpenFeignService.provider_Ok(id);
    }

    //请求超时
    @GetMapping("consumer/timeout/{id}")
    @HystrixCommand(fallbackMethod = "consumer_TimeOut_FallbackMethod",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
    })
    //@HystrixCommand
    public Object consumer_TimeOut(@PathVariable("id") Integer id){
        //int age = 10/0;
        return hystrixOpenFeignService.provider_Ok(id);
    }

    //特定类方法异常
    public String consumer_TimeOut_FallbackMethod(@PathVariable("id") Integer id)
    {
        return "消费者81,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    //全局fallback方法
    public String consumer_Global_FallbackMethod()
    {
        return "Global 全局异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}

@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或者zookeeper作为注册中心时注册服务
@EnableFeignClients //(basePackages = "com.zja.service.feign") // 开启 OpenFeign
@EnableHystrix  // 开启服务器降级/熔断机制
public class ZkOpenfeignHystixConsumer {
    public static void main(String[] args) {
        SpringApplication.run(ZkOpenfeignHystixConsumer.class, args);
    }
}
```

访问：     
http://localhost:18001/consumer/ok/{id}     
http://localhost:18001/consumer/timeout/{id}    
访问结果以测试接口为准     
