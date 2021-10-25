# REST 服务端提供者

## springboot 支持HTTPS与HTTP

#### 生成客户端证书

```
cd D:\sslza
# keytool -genkey -alias client -keypass 12345678 -keyalg RSA -keysize 2048 -validity 365 -storetype PKCS12 -keystore ./client.p12 -storepass 12345678
keytool -genkey -alias client -keypass 12345678 -keyalg RSA -keysize 2048 -validity 365 -ext san=ip:127.0.0.1 -storetype PKCS12 -keystore ./client.p12 -storepass 12345678
keytool -genkey -alias client -keypass 12345678 -keyalg RSA -keysize 2048 -validity 365 -ext san=ip:192.168.2.128 -storetype PKCS12 -keystore ./client.p12 -storepass 12345678

您的名字与姓氏是什么?
  [Unknown]:  aa
您的组织单位名称是什么?
  [Unknown]:  bb
您的组织名称是什么?
  [Unknown]:  cc
您所在的城市或区域名称是什么?
  [Unknown]:  dd
您所在的省/市/自治区名称是什么?
  [Unknown]:  Ch
该单位的双字母国家/地区代码是什么?
  [Unknown]:  Ch
CN=aa, OU=bb, O=cc, L=dd, ST=Ch, C=Ch是否正确?
  [否]:  y

#
cd D:\sslza
client.p12

```

#### Springboot 配置 HTTPS访问

1、将证书文件：client.p12复制到resources目录下

2、配置同时支持访问 HTTPS、HTTP

修改application.yml

```
server:
  port: 8443  #注意，这里是https访问的的端口号
  http:
    port: 8081
  ssl:
    key-store: file:F:\Study\mywebsite\websiteback\src\main\resources\client.p12
    key-store-password: 12345678
    key-store-type: PKCS12
    key-alias: client
```

3、SpringBoot 配置http tomcat web容器

- [TomcatConfig](./src/main/java/com/zja/config)

```
    /**
     * server.http.port
     */
    @Value("${server.http.port}")
    private int httpPort;

    /**
     * 创建一个新增的 Tomcat Web容器
     */
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        //添加 http
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(httpPort);
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }

```

