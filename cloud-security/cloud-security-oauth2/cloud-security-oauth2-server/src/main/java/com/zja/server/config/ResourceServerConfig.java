/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-11 10:13
 * @Since:
 */
package com.zja.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 资源服务器配置 访问权限配置
 *
 * 先将授权服务器变成资源服务器作测试
 */
@Deprecated
//@EnableResourceServer  // springboot2.4后可能弃用 拥有 WebSecurityConfigurerAdapter 相关能力
//@Configuration
//@Order(3) // ResourceServerConfig 是比 WebSecurityConfig 优先级低
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private DefaultTokenServices tokenServices;
    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        //无状态 默 true, 如果fasle关闭，则 accessToken 使用时的 session id 会被记录，后续请求不得携带 accessToken 也可以正常响应
        //resources.resourceId(RESOURCE_ID).stateless(fasle);
        resources.stateless(true);
        resources.tokenServices(tokenServices);
        //resources.tokenStore(tokenStore);
    }

    /*@Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(new OAuthRequestedMatcher())
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated();
    }

    private static class OAuthRequestedMatcher implements RequestMatcher {
        @Override
        public boolean matches(HttpServletRequest request) {
            String auth = request.getHeader("Authorization");
            // Determine if the client request contained an OAuth Authorization
            boolean haveOauth2Token = (auth != null) && auth.startsWith("Bearer");
            boolean haveAccessToken = request.getParameter("access_token") != null;
            return haveOauth2Token || haveAccessToken;
        }
    }*/
}
