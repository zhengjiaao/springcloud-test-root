/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-11 10:08
 * @Since:
 */
package com.zja.server.config;

import com.zja.server.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * security 配置
 *
 * 注解：@EnableGlobalMethodSecurity 启用全局方法级别注解
 *          prePostEnabled = true 解锁 @PreAuthorize 和 @PostAuthorize 两个注解
 *          securedEnabled = true 解锁 @Secured 有缺点，不支持SpEL表达式，角色必须以ROLE_开头
 *          jsr250Enabled = true 解锁 @DenyAll 和 @PermitAll 、@RolesAllowed
 */
@Order(2)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //解锁 @PreAuthorize 和 @PostAuthorize 两个注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailServiceImpl).passwordEncoder(passwordEncoder());
        auth.parentAuthenticationManager(authenticationManagerBean());

        /*auth.inMemoryAuthentication() //在内存中完成账号密码的检查
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("tom") //指定账号
                *//*.以前的".password("123")" 变成了 "password(new BCryptPasswordEncoder().encode("123"))"
                这相当于对内存中的密码进行Bcrypt编码加密
                如果比对时一致,说明密码正确,才允许登陆.
                *//*
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("ADMIN") //指定角色
                .and()
                .withUser("jerry") //指定账号
                .password("456")//指定密码
                .authorities("UPDATE")//指定角色
        ;*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略拦截
        web.ignoring().antMatchers("/oauth/check_token",
                "/assets/**", "/css/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/oauth/**", "/login"/*, "/logout/**"*/).permitAll() //不拦截路径
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();

        /*http.formLogin()
                .loginPage("/login")
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable().cors();*/

        /*http.requestMatchers()
                .antMatchers("/oauth/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .csrf().disable();*/
    }

    /**
     * 密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        //return new MyBCryptPasswordEncoder();
        //return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 身份验证管理器
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
