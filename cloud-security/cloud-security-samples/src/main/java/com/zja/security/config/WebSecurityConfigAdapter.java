/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-12-28 13:01
 * @Since:
 */
package com.zja.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 自定义：依旧使用Security默认提供的配置，然后重写部分配置
 *
 * 实现类一般在顺序上需要先于 spring-boot-starter-security 默认提供的配置，故此，一般配合@Order
 */
@Configuration
@Order(101)
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

    protected WebSecurityConfigAdapter() {
        //true 取消默认提供的安全相关Filters配置
        super(false);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 默认 无
    }

    /**
     * 安全拦截机制（最重要）
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //默认配置
        http.authorizeRequests((requests) -> {
            ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) requests.anyRequest()).authenticated();
        });
        http.formLogin();
        http.httpBasic();
    }

    /**
     * 自定义：安全拦截机制（最重要）
     */
/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //授权请求
        http.authorizeRequests()
                .antMatchers("/r/r1").hasAuthority("p1")
                .antMatchers("/r/r2").hasAuthority("p2")
                //所有/r/**的请求必须认证通过
                .antMatchers("/r/**").authenticated()
                //除了/r/**，其它的请求可以访问
                .anyRequest().permitAll();

        //自定义登录成功页面
        http
                //允许表单登录
                .formLogin()
                //自定义登录成功的页面地址
                .successForwardUrl("/login-success");

        http
                //关闭CSRF控制
                .csrf().disable();

        //会话管理配置
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionAuthenticationErrorUrl("/login")  // 会话身份验证错误 URL
                .invalidSessionUrl("/login?error=INVALID_SESSION"); //无效的会话 URL
    }
*/


    // 通过Override其他方法实现对web安全的定制
}
