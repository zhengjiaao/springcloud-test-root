# springcloud Config 配置服务端  

## 一、配置服务端 config-server

### 测试流程    
**依赖**  
```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
```

**application.yml** 
```yaml
server:
  port: 18888  # 服务指定的端口

spring:
  application:
    name: config-server  # 指定服务名称
  cloud:
    config:
      server:
        git:
          uri: https://github.com/zhengjiaao/springcloud-test-root.git  #配置文件所在仓库
          username: zhengjiaao       # git仓库的账号
          password: 123456   # git仓库的密码
          search-paths: cloud-config/config-catalog/yaml   # git仓库地址下的相对地址 多个用逗号","分割。
          default-label: master      # 配置文件分支，默认master
```

**github创建仓库**  
地址：https://github.com/zhengjiaao    
创建springcloud-test-root仓库，在仓库下面新建目录和文件  
```yaml
springcloud-test-root
   cloud-config
      yaml
        config.yaml
        config-dev.yaml
        config.pro.yaml
```
启动服务：   
ConfigServerApplication.java    
访问： 
格式：配置服务地址/应用名/配置/分支     
http://localhost:18888/{application}/{profile}[/{label}]       
http://localhost:18888/config/dev/master        
效果： 
```json
{
    "name":"config",
    "profiles":[
        "dev"
    ],
    "label":"master",
    "version":"35f704ff9e5e60abdd81b40fff74d33320c38fd5",
    "state":null,
    "propertySources":[
        {
            "name":"https://github.com/zhengjiaao/springcloud-test-root.git/cloud-config/config-catalog/yaml/config-dev.yaml",
            "source":{
                "attributes.config":"config-dev.yaml",
                "attributes.list[0]":1,
                "attributes.list[1]":2,
                "attributes.map.name":"张三",
                "attributes.map.age":20,
                "stringvalue":"dev",
                "dynamicvalue.data":"asd",
                "dynamicvalue.value":123
            }
        },
        {
            "name":"https://github.com/zhengjiaao/springcloud-test-root.git/cloud-config/config-catalog/yaml/config.yaml",
            "source":{
                "config":"properties"
            }
        }
    ]
}

```

# 二、配置客户端 config-web    
 依赖 
 ```xml
         <dependency>
             <groupId>org.springframework.cloud</groupId>
             <artifactId>spring-cloud-starter-config</artifactId>
         </dependency>
```

创建bootstrap.yaml    
> bootstrap.yaml 比application.yml先加载    
```yaml
server:
  port: 18006
spring:
  profiles:
    active: @profile.active@
  application:
    name: @artifactId@
  #开启热部署
  devtools:
    restart:
      enabled: true
  cloud:
    config:
      enabled: true
      name: @config.name@  # 对应前配置文件中的{application}
      profile: @config.profile@  # 对应前配置文件中的{profile}
      label: @config.label@  # 对应前配置文件的git分支{label}
      uri: @config.host@  # 配置中心的地址
```


测试类 
```java
    @Value("${stringvalue}")
    private String stringvalue;

    @GetMapping("get")
    public Object myAttributes() {
        Map map = new HashMap();
        map.put("stringvalue", stringvalue);
        return map;
    }
```

启动类 ConfigWebApplication.java       
访问：http://localhost:18006/get   
效果:     
```java
"stringvalue":"dev"
```