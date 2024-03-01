# cloud-nacos-config-auth          

## 开启 nacos权限认证

> 注意，根据 nacos-server 2.x 版本实现

nacos-server-2.2.3 服务端`conf/application.properties`配置

```properties
### If turn on auth system:
#nacos.core.auth.enabled=false
#启动nacos server权限认证
nacos.core.auth.enabled=true

### Since 1.4.1, Turn on/off white auth for user-agent: nacos-server, only for upgrade from old version.
nacos.core.auth.enable.userAgentAuthWhite=false

### Since 1.4.1, worked when nacos.core.auth.enabled=true and nacos.core.auth.enable.userAgentAuthWhite=false.
### The two properties is the white list for auth and used by identity the request from other server.
nacos.core.auth.server.identity.key=example
nacos.core.auth.server.identity.value=example

### The default token (Base64 String):
nacos.core.auth.plugin.nacos.token.secret.key=VGhpc0lzTXlDdXN0b21TZWNyZXRLZXkwMTIzNDU2Nzg=
```

> 注意，以上为简单示例，仅推荐测试使用，如果你要在正式环境使用，请自定义 `identity.key` 、`identity.value`、`token.secret.key`
> 等值。  
> 特别注意：`token.secret.key=` 值必须要是自己生成的

## springboot nacos 添加认证

```yaml
spring:
  cloud:
    nacos:
      config: # ${prefix}-${spring.profiles.active}.${file-extension}
        # 开启nacos server 服务端的权限认证
        username: nacos
        password: nacos
```