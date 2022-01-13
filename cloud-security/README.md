# cloud-security

**参考地址：**

- [spring-security 官网](https://spring.io/projects/spring-security)
- [OAuth2 官方说明](https://oauth.net/2/)
- [OAuth2 四种授权方式详解-阮一峰](https://www.ruanyifeng.com/blog/2019/04/oauth-grant-types.html)
- [OAuth2 四种授权方式详解](https://blog.csdn.net/u012948161/article/details/109743383)
- [Spring Security 中的 hasRole 和 hasAuthority 有区别吗？](https://cloud.tencent.com/developer/article/1703187)



```xml
<!--已弃用->迁移至 springboot-->
<!--<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-security</artifactId>
</dependency>-->

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

```java
@RestController
public class ApiController {
    /**
     * http://127.0.0.1:8080/hello
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
```

访问：http://127.0.0.1:8080/hello
会跳转至登录页面：http://127.0.0.1:8080/login
默认账户：user/控制台生成的随机密码

```yaml
spring:
  security:
    user:
      name: user     # 默 user
      password: pass # 默 空 自动生成随机密码
```

两种方式自定义扩展 security 配置：

- 基于security默认配置再扩展
- 完全自定义扩展(`不使用security默认配置`)

### 基于security默认配置再扩展

```java
@Configuration
@Order(-1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
}
```

### 完全自定义扩展

```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {
}
```
