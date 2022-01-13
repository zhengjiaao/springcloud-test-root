/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-11 14:28
 * @Since:
 */
package com.zja.client.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 */
@EnableOAuth2Sso
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /* @Autowired
     private EnvironmentUtils environmentUtils;
 */
    /*@Override
    public void configure(WebSecurity web) throws Exception {
        //css、js等忽略拦截
        web.ignoring().antMatchers("/assets/**", "/css/**", "/images/**");
    }*/

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //依据环境不同，进行配置
        /*if ("local".equals(environmentUtils.getActiveProfile())) {
            http.authorizeRequests().anyRequest().permitAll();
        }else {
            http.logout().logoutSuccessUrl("http://localhost:8080/logout")
                    .and()
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .csrf().disable();
        }*/

        //退出单点登录系统
        http.logout().logoutSuccessUrl("http://localhost:8080/logout")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        //仅退出当前系统，无法退出单点登录系统
        /*http.logout()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();*/
    }

}
