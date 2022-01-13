# cloud-security-oauth2

- [OAuth2 官方说明](https://oauth.net/2/)
- [OAuth2 四种授权方式详解-阮一峰](https://www.ruanyifeng.com/blog/2019/04/oauth-grant-types.html)
- [OAuth2 四种授权方式详解](https://blog.csdn.net/u012948161/article/details/109743383)

##  @EnableAuthorizationServer 注解

@EnableAuthorizationServer 注解 引入了一些 OAuth2 相关的端点        
包含以下的端点:
AuthorizationEndpoint 根据用户认证获得授权码，有下面两个方法：      
- /oauth/authorize - GET
- /oauth/authorize - POST

TokenEndpoint 客户端根据授权码获取 token      
- /oauth/token - GET
- /oauth/token - POST

CheckTokenEndpoint 可以用于远程解码令牌       
- /oauth/check_token

WhitelabelApprovalEndpoint 显示授权服务器的确认页      
- /oauth/confirm_access

WhitelabelErrorEndpoint 显示授权服务器的错误页     
- /oauth/error

## 认证流程-端点

- /oauth/authorize(授权端，授权码模式使用)
- /oauth/token(令牌端，获取 token)
- /oauth/check_token(资源服务器用来校验token)
- /oauth/confirm_access(用户发送确认授权)
- /oauth/error(认证失败)
- /oauth/token_key(如果使用JWT，可以获的公钥用于 token 的验签)

## token 相关
- 获取access_token请求（/oauth/token）   
    - 请求所需参数：client_id、client_secret、grant_type、username、password
```
http://localhost/oauth/token?client_id=demoClientId&client_secret=demoClientSecret&grant_type=password&username=demoUser&password=50575tyL86xp29O380t1
```
- 检查头肯是否有效请求（/oauth/check_token）
    -  请求所需参数：token
```
http://localhost/oauth/check_token?token=f57ce129-2d4d-4bd7-1111-f31ccc69d4d1
```
- 刷新token请求（/oauth/token）
    - 请求所需参数：grant_type、refresh_token、client_id、client_secret
    - 其中grant_type为固定值：grant_type=refresh_token
```
http://localhost/oauth/token?grant_type=refresh_token&refresh_token=fbde81ee-f419-42b1-1234-9191f1f95be9&client_id=demoClientId&client_secret=demoClientSecret
```

## oauth 认证相关
- 授权访问
    - 需要认证保护
    - 请求所需参数：无
```
curl -i http://localhost:8080/oauth/confirm_access
```
- 认证错误
    - 可以不用认证保护
    - 请求所需参数：无
```
curl -i http://localhost:8080/oauth/error
```


### 授权码认证
```
#认证流程
1、获取授权码，如果未登录(session无效)，将跳转到登录页面，返回授权码code
http://localhost:8080/oauth/authorize?response_type=code&client_id=oa_client&redirect_uri=http://www.baidu.com&state=123

2、成功登陆后获取token，根据授权码code 获取令牌token  授权码code只能使用一次
http://localhost:8080/oauth/token?code=V1gxaS&grant_type=authorization_code&redirect_uri=http://www.baidu.com&client_id=oa_client&client_secret=oa_secret

{
    "access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NDE5MzU2MDMsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiY2UxNjEyZjUtYzQyYS00YzgxLThlZWItMDIxY2IxZmMzNTg3IiwiY2xpZW50X2lkIjoiY2xpZW50X2lkX29hIiwic2NvcGUiOlsicmVhZCJdfQ.1cIXtMBMxxwCwYLx_MRkSUTAPaNNIQTBxX3BUKXuxqc",
    "token_type":"bearer",
    "refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiXSwiYXRpIjoiY2UxNjEyZjUtYzQyYS00YzgxLThlZWItMDIxY2IxZmMzNTg3IiwiZXhwIjoxNjQ0NDg0NDAzLCJhdXRob3JpdGllcyI6WyJhZG1pbiJdLCJqdGkiOiJlOTljMWVkNS03YmMyLTQ4YTEtYTUwOS1jNjMxNWYxYmM1NGYiLCJjbGllbnRfaWQiOiJjbGllbnRfaWRfb2EifQ.KGhcyB0wKaklBHxmgwsVTsA7Om_pM5GGK6dpJ-WfSjM",
    "expires_in":43199,
    "scope":"read",
    "jti":"ce1612f5-c42a-4c81-8eeb-021cb1fc3587"
}

3、根据token令牌获取资源
http://localhost:8080/rest/api/current-user?access_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NDE5MzU2MDMsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiY2UxNjEyZjUtYzQyYS00YzgxLThlZWItMDIxY2IxZmMzNTg3IiwiY2xpZW50X2lkIjoiY2xpZW50X2lkX29hIiwic2NvcGUiOlsicmVhZCJdfQ.1cIXtMBMxxwCwYLx_MRkSUTAPaNNIQTBxX3BUKXuxqc

4、刷新token令牌 非必要的
http://localhost:8080/oauth/token?

5、获取当前登录用户信息
http://localhost:8080/rest/api/current-user

```
