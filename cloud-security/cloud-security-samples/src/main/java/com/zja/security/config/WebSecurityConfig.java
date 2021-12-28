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

/**
 * 自定义：@EnableWebSecurity 不使用Security默认提供的配置，完全自建
 *
 * 此方案需要开发者对 Spring Security 框架本身以及 Web 安全本身有很深的理解，不到迫不得已，最好不要这么做，威力大，风险也大
 */
@Configuration
//@EnableWebSecurity
public class WebSecurityConfig  {
    /*@Bean
    public AuthenticationManager authenticationManager() {
        // ...
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        // ...
    }

    @Bean
    public SecurityFilterChain mySecurityFilterChain() {
        // ...
    }*/

    // 其他web安全相关组件和依赖配置}
}
