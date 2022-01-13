# cloud-security-oauth2-server

- [OAuth2 官方说明](https://oauth.net/2/)
- [OAuth2 四种授权方式详解-阮一峰](https://www.ruanyifeng.com/blog/2019/04/oauth-grant-types.html)
- [OAuth2 四种授权方式详解](https://blog.csdn.net/u012948161/article/details/109743383)

## OAuth2 Token 令牌
```
# OAuth2正宗 授权码code方式 获取token
http://localhost:8080/oauth/token?
    grant_type=authorization_code
    &client_id=test_client
    &client_secret=test_secret
    &code=${code}
    &redirect_uri=http://www.baidu.com

#校验token是否有效    非必要的
http://localhost:8080/oauth/check_token?token={access_token}

#刷新token令牌 非必要的
http://localhost:8080/oauth/token?
    grant_type=refresh_token
    &client_id=test_client
    &client_secret=test_secret
    &refresh_token={refresh_token}

#接口获取公钥  需要配置密钥信息 非必要的
http://localhost:8080/oauth/token_key
```

## OAuth2 标准认证流程

### 授权码认证-正宗的
认证流程：
1、通过`oauth/authorize` 认证 返回`授权码code`
2、再通过`/oauth/token` 根据`授权码code`获取`token令牌`
3、最后根据`token令牌`获取调用`资源接口`

入参说明：

| 参数 | 类型 | 默认值 | 说明 |
| ---- | ---- | ---- | ---- |
|response_type| string | code | 固定值 |
|client_id| string | | 客户端应用ID(由平台发放) |
|redirect_uri| string | | 认证成功后跳转地址 |
|state| int or string | | 状态码，第三方系统自定义 |

```
# 授权认证，登录成功用户确认授权后，返回授权码
http://localhost:8080/oauth/authorize?
    response_type=code
    &client_id=test_client
    &redirect_uri=http://www.baidu.com
    &state=1000

#响应 返回地址，携带授权码code
https://www.baidu.com?code=${code}&state=123


#用授权码申请 access_token   授权码code只能获取一次token令牌
http://localhost:8080/oauth/token?
    grant_type=authorization_code
    &client_id=test_client
    &client_secret=test_secret
    &code=${code}
    &redirect_uri=http://www.baidu.com

#响应
{
    "access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NDE5MzU2MDMsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiY2UxNjEyZjUtYzQyYS00YzgxLThlZWItMDIxY2IxZmMzNTg3IiwiY2xpZW50X2lkIjoiY2xpZW50X2lkX29hIiwic2NvcGUiOlsicmVhZCJdfQ.1cIXtMBMxxwCwYLx_MRkSUTAPaNNIQTBxX3BUKXuxqc",
    "token_type":"bearer",
    "refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiXSwiYXRpIjoiY2UxNjEyZjUtYzQyYS00YzgxLThlZWItMDIxY2IxZmMzNTg3IiwiZXhwIjoxNjQ0NDg0NDAzLCJhdXRob3JpdGllcyI6WyJhZG1pbiJdLCJqdGkiOiJlOTljMWVkNS03YmMyLTQ4YTEtYTUwOS1jNjMxNWYxYmM1NGYiLCJjbGllbnRfaWQiOiJjbGllbnRfaWRfb2EifQ.KGhcyB0wKaklBHxmgwsVTsA7Om_pM5GGK6dpJ-WfSjM",
    "expires_in":43199,
    "scope":"read",
    "jti":"ce1612f5-c42a-4c81-8eeb-021cb1fc3587"
}

#用access_token令牌 访问资源 未成功
http://localhost:8080/rest/api/current-user?access_token={access_token}

```
