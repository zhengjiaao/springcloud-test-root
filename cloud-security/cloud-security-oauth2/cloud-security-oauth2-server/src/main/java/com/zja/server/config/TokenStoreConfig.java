/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-12 9:16
 * @Since:
 */
package com.zja.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * token 令牌管理配置
 */
@Configuration
public class TokenStoreConfig {

    //@Autowired
    //private RedisConnectionFactory connectionFactory;

    /**
     * Jwt 访问令牌转换器
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //设置签名密钥
        jwtAccessTokenConverter.setSigningKey("123456");
        return jwtAccessTokenConverter;
    }

    /**
     * token令牌 存储管理
     *
     * 认证的 token 可以存储到 redis
     */
    @Bean
    public TokenStore tokenStore() {
        //redis 持久化令牌
        //return new RedisTokenStore(connectionFactory);
        //Jwt 存储令牌
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * token令牌 审批存储管理
     */
    @Bean
    public ApprovalStore approvalStore() {
        TokenApprovalStore approvalStore = new TokenApprovalStore();
        approvalStore.setTokenStore(tokenStore());
        return approvalStore;
    }

    /**
     * 默认令牌服务配置  不生效
     *
     * 自定义TokenServices的时候，需要设置@Primary，否则报错
     */
    @Bean
    @Primary
    public DefaultTokenServices tokenServices(JwtAccessTokenConverter jwtAccessTokenConverter, ClientDetailsService clientDetailsService) {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        //设置令牌存储
        defaultTokenServices.setTokenStore(tokenStore());
        //设置 access_token 支持刷新令牌 默 false
        defaultTokenServices.setSupportRefreshToken(true);
        //设置 access_token 访问令牌有效期 秒 默 12小时
//        defaultTokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
        defaultTokenServices.setAccessTokenValiditySeconds(60);
        //设置 refresh_token 刷新令牌有效期 秒 默 30天
//        defaultTokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 30);
        defaultTokenServices.setRefreshTokenValiditySeconds(60 * 2);
        //非必须的 设置客户详细信息服务
        defaultTokenServices.setClientDetailsService(clientDetailsService);

        //必须的 设置令牌增强器  否则 此 DefaultTokenServices Bean 不生效,客户端报错信息让如下
        //错误信息 : localhost 将您重定向的次数过多 ERR_TOO_MANY_REDIRECTS
        defaultTokenServices.setTokenEnhancer(jwtAccessTokenConverter);

        return defaultTokenServices;
    }
}
