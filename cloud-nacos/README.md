# cloud-nacos 配置中心、服务注册和发现  

**nacos相关文档**  
> https://nacos.io/zh-cn/docs/use-nacos-with-dubbo.html


## cloud-nacos     
>cloud-nacos 主pom.xml    
```xml
	<modules>
		<module>nacos-config</module>
		<module>nacos-provider</module>
		<module>nacos-consumer</module>
		<module>nacos-dubbo-api</module>
	</modules>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
			<dependency>
				<groupId>com.alibaba.cloud</groupId>
				<artifactId>spring-cloud-alibaba-dependencies</artifactId>
				<version>${spring-cloud-alibaba-dependencies.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
			<dependency>
				<groupId>org.projectlombok</groupId>
				<artifactId>lombok</artifactId>
				<version>${lombok.version}</version>
			</dependency>
		</dependencies>
	</dependencyManagement>

```

### nacos-config 配置中心
> nacos-config pom.xml
```xml
    <!-- alibaba cloud-->
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
    </dependency>

    <profiles>
        <profile>
            <id>dev</id>
            <properties>
                <profile.active>dev</profile.active>
                <!-- Nacos 配置服务 -->
                <nacos.host>192.168.159.128</nacos.host>
                <nacos.port>8848</nacos.port>
                <nacos.namespace>nacos_config</nacos.namespace>
                <nacos.group>DEFAULT_GROUP</nacos.group>
                <nacos.file_extension>yaml</nacos.file_extension>
            </properties>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
        </profile>
        <profile>
            <id>pro</id>
            <properties>
                <profile.active>pro</profile.active>
                <!-- Nacos 配置服务 -->
                <nacos.host>192.168.159.128</nacos.host>
                <nacos.port>8848</nacos.port>
                <nacos.namespace>nacos_config</nacos.namespace>
                <nacos.group>DEFAULT_GROUP</nacos.group>
                <nacos.file_extension>yaml</nacos.file_extension>
            </properties>
        </profile>
        <profile>
            <id>test</id>
            <properties>
                <profile.active>test</profile.active>
                <!-- Nacos 配置服务 -->
                <nacos.host>192.168.159.128</nacos.host>
                <nacos.port>8848</nacos.port>
                <nacos.namespace>nacos_config</nacos.namespace>
                <nacos.group>DEFAULT_GROUP</nacos.group>
                <nacos.file_extension>yaml</nacos.file_extension>
            </properties>
        </profile>
    </profiles>

    <build>
        <!--解决@profile.active@报错-->
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <excludes>
                    <exclude>*.properties</exclude>
                    <exclude>*.yaml</exclude>
                </excludes>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
                <includes>
                    <include>bootstrap.properties</include>
                    <include>bootstrap.yaml</include>
                    <include>application.properties</include>
                    <include>application-${profile.active}.properties</include>
                    <include>application.yaml</include>
                    <include>application-${profile.active}.yaml</include>
                </includes>
            </resource>
        </resources>
    </build>
```
bootstrap.yaml  
> bootstrap.yaml 比 application.yml 先加载  
```yaml
server:
  port: 18001

spring:
  profiles:
    active: @profile.active@
  application:
    name: @artifactId@
  main:
    allow-bean-definition-overriding: true
  cloud:
    nacos:
      config:  # ${prefix}-${spring.profiles.active}.${file-extension}
        server-addr: @nacos.host@:@nacos.port@
        namespace: @nacos.namespace@
        group: @nacos.group@
        file-extension: @nacos.file_extension@
```

java相关代码    
```java
@Data
@Component
@ConfigurationProperties(prefix = "attributes")
public class MyAttributes {

    private String config;
    private List<String> list;
    private HashMap<String, String> map;
}

@RefreshScope  //自动刷新nacos配置
@RestController
public class HelloWorldController {

    @Autowired
    private MyAttributes myAttributes;

    @GetMapping("v1/hello")
    public Object hello() {
        return myAttributes;
    }
}
```

nacos 配置    
> 需要先安装 nacos   

```yaml
attributes:
  # 单个值
  config: nacos-config-dev.yaml
  # list
  list:
    - 1
    - 2
  # map
  map:
    name: 张三
    age: 20
```
访问：http://localhost:18001/v1/hello  
访问结果：   
```json
{"config":"nacos-config-dev.yaml","list":["1","2"],"map":{"name":"张三","age":"20"}}
```

### nacos 服务注册和发现   
**nacos-provoder**  
> nacos-provoder  pom.xml  
```xml
    <dependencies>
        <!-- Spring Boot dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-actuator</artifactId>
        </dependency>

        <!-- Spring Cloud dependencies -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-dubbo</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>

        <dependency>
            <groupId>com.zja</groupId>
            <artifactId>nacos-dubbo-api</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

    </dependencies>

    <profiles>
        <profile>
            <id>dev</id>
            <properties>
                <profile.active>dev</profile.active>
                <!-- Nacos 配置服务 -->
                <nacos.host>192.168.159.128</nacos.host>
                <nacos.port>8848</nacos.port>
                <nacos.namespace>nacos_dubbo</nacos.namespace>
                <!--<nacos.group>NACOS_GROUP</nacos.group>-->
                <nacos.file_extension>yaml</nacos.file_extension>
            </properties>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
        </profile>
        <profile>
            <id>pro</id>
            <properties>
                <profile.active>pro</profile.active>
                <!-- Nacos 配置服务 -->
                <nacos.host>192.168.159.128</nacos.host>
                <nacos.port>8848</nacos.port>
                <nacos.namespace>nacos_dubbo</nacos.namespace>
                <!--<nacos.group>NACOS_GROUP</nacos.group>-->
                <nacos.file_extension>yaml</nacos.file_extension>
            </properties>
        </profile>
        <profile>
            <id>test</id>
            <properties>
                <profile.active>test</profile.active>
                <!-- Nacos 配置服务 -->
                <nacos.host>192.168.159.128</nacos.host>
                <nacos.port>8848</nacos.port>
                <nacos.namespace>nacos_dubbo</nacos.namespace>
                <!--<nacos.group>NACOS_GROUP</nacos.group>-->
                <nacos.file_extension>yaml</nacos.file_extension>
            </properties>
        </profile>
    </profiles>

    <build>
        <!--解决@profile.active@报错-->
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <excludes>
                    <exclude>*.properties</exclude>
                    <exclude>*.yaml</exclude>
                </excludes>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
                <includes>
                    <include>bootstrap.properties</include>
                    <include>bootstrap.yaml</include>
                    <include>application.properties</include>
                    <include>application-${profile.active}.properties</include>
                    <include>application.yaml</include>
                    <include>application-${profile.active}.yaml</include>
                </includes>
            </resource>
        </resources>
    </build>
```
java相关代码    
```java
@DubboService
public class HelloServiceImpl implements HelloService {

    @Override
    public String getName(){
        return "李四";
    }

    @Override
    public User getUser(){
        User user = new User();
        user.setName("张三");
        user.setAge(24);
        return user;
    }
}
```
bootstrap.yaml
```yaml
server:
  port: 18001
spring:
  profiles:
    active: @profile.active@
  application:
    name: @artifactId@
  main:
    allow-bean-definition-overriding: true
  cloud:
    nacos:
      discovery:
        server-addr: @nacos.host@:@nacos.port@
        namespace: nacos_dubbo
      config:
        server-addr: @nacos.host@:@nacos.port@
        namespace: nacos_dubbo
        file-extension: @nacos.file_extension@

dubbo:
  registry:
    address: spring-cloud://@nacos.host@
  #    parameters:
  #      namespace: @nacos.namespace@  # 可选-命名空间
  scan:
    base-packages: com.zja
  protocol:
    name: dubbo
    port: 4001
```

**nacos-dubbo-api**     
> pom.xml   
```xml
    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
    </dependencies>
```
java相关代码
```java
@Data
public class User implements Serializable {
    private String name;
    private int age;
}

public interface HelloService {
    String getName();
    User getUser();
}
```
**nacos-consumer**      
> pom.xml   
```xml
    <dependencies>
        <!-- Spring Boot dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-dubbo</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>

        <dependency>
            <groupId>com.zja</groupId>
            <artifactId>nacos-dubbo-api</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
    </dependencies>

    <profiles>
        <profile>
            <id>dev</id>
            <properties>
                <profile.active>dev</profile.active>
                <!-- Nacos 配置服务 -->
                <nacos.host>192.168.159.128</nacos.host>
                <nacos.port>8848</nacos.port>
                <nacos.namespace>nacos_dubbo</nacos.namespace>
                <!--<nacos.group>NACOS_GROUP</nacos.group>-->
                <nacos.file_extension>yaml</nacos.file_extension>
            </properties>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
        </profile>
        <profile>
            <id>pro</id>
            <properties>
                <profile.active>pro</profile.active>
                <!-- Nacos 配置服务 -->
                <nacos.host>192.168.159.128</nacos.host>
                <nacos.port>8848</nacos.port>
                <nacos.namespace>nacos_dubbo</nacos.namespace>
                <!--<nacos.group>NACOS_GROUP</nacos.group>-->
                <nacos.file_extension>yaml</nacos.file_extension>
            </properties>
        </profile>
        <profile>
            <id>test</id>
            <properties>
                <profile.active>test</profile.active>
                <!-- Nacos 配置服务 -->
                <nacos.host>192.168.159.128</nacos.host>
                <nacos.port>8848</nacos.port>
                <nacos.namespace>nacos_dubbo</nacos.namespace>
                <!--<nacos.group>NACOS_GROUP</nacos.group>-->
                <nacos.file_extension>yaml</nacos.file_extension>
            </properties>
        </profile>
    </profiles>

    <build>
        <!--解决@profile.active@报错-->
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <excludes>
                    <exclude>*.properties</exclude>
                    <exclude>*.yaml</exclude>
                </excludes>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
                <includes>
                    <include>bootstrap.properties</include>
                    <include>bootstrap.yaml</include>
                    <include>application.properties</include>
                    <include>application-${profile.active}.properties</include>
                    <include>application.yaml</include>
                    <include>application-${profile.active}.yaml</include>
                </includes>
            </resource>
        </resources>
    </build>
```

java相关代码    
```java
@RefreshScope  //自动刷新nacos配置
@RestController
public class ConfigController {

    //nacos 配置
    @Value("${name}")
    private String name;

    @GetMapping("name")
    public String getname(){
        return name;
    }
}

@RestController
public class DubboController {

    @DubboReference
    private HelloService helloService;

    @GetMapping("v1/dubbo/name")
    public String name(){
        return helloService.getName();
    }

    @GetMapping("v1/dubbo/user")
    public User user(){
        return helloService.getUser();
    }

}

@RestController
public class ServiceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    //根据服务获取实例信息
    @GetMapping("getInstances")
    public List<ServiceInstance> getInstances(String serviId){
        //查询指定服务的所有实例信息
        //无论使用什么做注册中心都可以使用consul、eureka、zookeeper
        return discoveryClient.getInstances(serviId);
    }

    //获取服务
    @GetMapping("getServices")
    public List<String> getServices(){
        //查询该注册中心有多少个服务
        return discoveryClient.getServices();
    }
}
```
bootstrap.yaml  
```yaml
server:
  port: 18002
spring:
  profiles:
    active: @profile.active@
  application:
    name: @artifactId@
  main:
    allow-bean-definition-overriding: true
  cloud:
    nacos:
      discovery:
        server-addr: @nacos.host@:@nacos.port@
        namespace: nacos_dubbo
      config:
        server-addr: @nacos.host@:@nacos.port@
        namespace: nacos_dubbo
        file-extension: @nacos.file_extension@

dubbo:
  registry:
    address: spring-cloud://@nacos.host@
  consumer:
    check: false
    timeout: 60000
  cloud:
    subscribed-services: nacos-provider  # 多个以","分割
  protocol:
    name: dubbo
    port: 4002
```

nacos 配置        
> 需要先安装 nacos   
> 命名空间   nacos_config , nacos_dubbo
> 命名ID     nacos_config , nacos_dubbo

创建配置        
nacos-consumer-dev.yaml     
```yaml
name: nacos-consumer-dev.yaml
```

访问： 
- http://localhost:18002/v1/dubbo/name
- http://localhost:18002/v1/dubbo/user
- http://localhost:18002/name
- http://localhost:18002/getServices
- http://localhost:18002/getInstances?serviId=nacos-consumer
访问结果：   
> 自己访问试试把