/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-11 14:33
 * @Since:
 */
package com.zja.client.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * http://localhost:8081/oa/rest/api/
 */
@RestController
@RequestMapping("rest/api")
public class RestApiController {

    @GetMapping("/current-user")
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/authentication")
    public Authentication me(Authentication authentication) {
        return authentication;
    }

    /**
     * @PreAuthorize 进入方法前的权限验证，需要的权限表达式
     *
     * 根据这个注解所需要的权限，再和当前登录的用户角色所拥有的权限对比，如果用户的角色权限集Set中有这个权限，则放行；没有，拒绝
     */
    @PreAuthorize("hasAuthority('member:save')")
    @PostMapping("/add")
    public String add() {
        return "add";
    }

    /**
     * @PostAuthorize 方法调用后进行权限检查
     *
     * 不常用
     */
    @PostAuthorize("hasAuthority('member:get')")
    @GetMapping("/get")
    public String get() {
        return "get";
    }
}
