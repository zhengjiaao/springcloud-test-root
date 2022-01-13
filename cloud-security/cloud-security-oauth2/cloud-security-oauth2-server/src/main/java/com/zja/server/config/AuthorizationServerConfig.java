/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-11 10:05
 * @Since:
 */
package com.zja.server.config;

import com.zja.server.exception.OAuth2WebResponseExceptionTranslator;
import com.zja.server.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * OAuth2 认证服务器 配置
 *
 * 参考：https://www.pianshen.com/article/9757312243/
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /*@Autowired
    private DataSource dataSource;*/

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private DefaultTokenServices tokenServices;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OAuth2WebResponseExceptionTranslator oAuth2WebResponseExceptionTranslator;


    /**
     * 授权服务器安全配置器
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")          //令牌密钥访问,全部允许
                .checkTokenAccess("isAuthenticated()")  //检查令牌访问, 已通过身份验证
                .allowFormAuthenticationForClients();    //允许客户端的表单身份验证

        //密码编码方式
        //security.passwordEncoder(passwordEncoder);
    }

    /**
     * 客户端详细信息服务配置
     *
     * 可以把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //客户详细信息
        clients.withClientDetails(clientDetails());

        //在内存中客户端详细信息服务生成器
        /*clients.inMemory() // 使用in-memory 内存存储
                .withClient("client_id_oa") // client_id   android
                .redirectUris("http://www.baidu.com")
                .secret(passwordEncoder.encode("client_secret_oa"))  // client_secret   android
                //.autoApprove(true)  //如果为true　则不会跳转到授权页面【oauth_approval.html】，而是直接同意授权返回redirectUris+code
                .authorizedGrantTypes("password", "authorization_code", "refresh_token") // 该client允许的授权类型
                //支持的授权五种类型
                //.authorizedGrantTypes("authorization_code", "password","client_credentials","implicit","refresh_token")// 该client允许的授权类型authorization_code,password,refresh_token,implicit,client_credentials
                .scopes("read") //允许的授权范围
                .and()
                .withClient("client_id_webapp") // client_id
                .scopes("read")
                //.secret("client_secret_webapp")  // client_secret
                .authorizedGrantTypes("implicit")  // 该client允许的授权类型
                .and()
                .withClient("browser")
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("read");*/

        //Jdbc 客户端详细信息服务生成器-常用
        //通过JDBC去查询数据库oauth_client_details表验证clientId信息
        //clients.jdbc(this.dataSource).clients(this.clientDetails());
        //clients.withClientDetails(this.clientDetails());

        // 客户端信息通过Redis去取得验证
        /*final RedisClientDetailsServiceBuilder builder = new RedisClientDetailsServiceBuilder();
        clients.setBuilder(builder);*/
    }

    /**
     * 授权服务器端点配置器
     *
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        //请求方式 支持 GET、POST 允许的token令牌端点请求方法  TokenEndpoint
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        //自定义默认获取令牌地址 原有路径将失效
        //endpoints.pathMapping("/oauth/token","/oauth2/token");

        //指定token存储位置 令牌管理-方式一配置 授权服务器令牌服务
        endpoints.tokenServices(tokenServices);

        //指定token存储位置 令牌管理-方式二配置 访问令牌转换器和存储方式
//        endpoints.accessTokenConverter(jwtAccessTokenConverter)
//                .tokenStore(tokenStore);
        // 自定义生成令牌
        //endpoints.tokenEnhancer(tokenEnhancer);

        //用户账号密码认证,动态查询用户信息 若需要 refresh_token 一定要加上
        endpoints.userDetailsService(userDetailServiceImpl);
        //指定认证管理器 支持密码模式
        endpoints.authenticationManager(authenticationManager);

        //OAuth2 自定义异常处理 自定义返回异常格式和提示信息
        //处理 {@link ExceptionTranslationFilter} 抛出的异常
        endpoints.exceptionTranslator(oAuth2WebResponseExceptionTranslator);
    }

    @Bean
    public ClientDetailsService clientDetails() throws Exception {
        //Jdbc 客户端详细信息服务
//        return new JdbcClientDetailsService(dataSource);

        //内存客户端详细信息服务
        return new InMemoryClientDetailsServiceBuilder()

                // client test application
                //客户端 client-id
                .withClient("test_client")
                //客户端 client-secret
                .secret(passwordEncoder.encode("test_secret"))
                //范围："all","read","write"
                .scopes("all")
                //支持的授权五种类型
                .authorizedGrantTypes("authorization_code", "refresh_token", "password", "client_credentials", "implicit")
                //注册的重定向URI
                .redirectUris("http://www.baidu.com")
                // 参考：DefaultTokenServices 默认配置
                .accessTokenValiditySeconds(7200)
                //false 手动授权(默)：会跳转到授权页面【oauth_approval.html】
                //true 自动授权：登录后直接返回 redirectUris+code
                .autoApprove(true)

                .and()

                // client oa application
                .withClient("oa_client")
                .secret(passwordEncoder.encode("oa_secret"))
                //"all","read", "write"
                .scopes("all")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .redirectUris("http://localhost:8081/oa/login", "http://www.baidu.com")
                .accessTokenValiditySeconds(7200)
                .autoApprove(true)

                .and()

                // client qq application
                .withClient("qq_client")
                .secret(passwordEncoder.encode("qq_secret"))
                .scopes("all")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .redirectUris("http://localhost:8082/qq/login")
                .accessTokenValiditySeconds(7200)
                .autoApprove(true)

                .and()
                .build();
    }


    /**
     * 授权码管理服务 颁发和存储授权代码的服务，默 基于内存存储的随机值授权码
     *
     * {@link AuthorizationCodeServices} 就是关于这个授权码code颁发后怎么存储的对象
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        //JDBC授权码服务
        //return new JdbcAuthorizationCodeServices(dataSource);
        //内存授权码服务
        return new InMemoryAuthorizationCodeServices();
    }

    /*---------------OAuth2 四种 授权模式-------------*/


    /**
     * OAuth2 四种 授权模式
     *      授权码模式-最常用
     *      密码模式-常用
     *      客户端模式
     *      简化模式
     */
    //@Bean
    public AbstractTokenGranter tokenGranter() {
        //授权码授权码模式 正宗的 OAuth2的授权模式，客户端先将用户导向认证服务器，登录后获取授权码，然后进行授权，最后根据授权码获取访问令牌
        //return new AuthorizationCodeTokenGranter();
        //密码模式-常用 客户端直接向用户获取用户名和密码，之后向认证服务器获取访问令牌
        //return new ResourceOwnerPasswordTokenGranter();
        //客户端模式 客户端直接通过客户端认证（比如client_id和client_secret）从认证服务器获取访问令牌
        //return new ClientCredentialsTokenGranter();
        //简化模式 和授权码模式相比，取消了获取授权码的过程，直接获取访问令牌
        //return new ImplicitTokenGranter();
        return null;
    }
}
