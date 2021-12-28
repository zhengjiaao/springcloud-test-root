# cloud-security-samples

```xml
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
