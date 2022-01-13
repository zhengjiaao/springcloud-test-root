/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-11 14:25
 * @Since:
 */
package com.zja.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("rest/api")
public class RestApiController {

    //当前登录用户信息
    /*@GetMapping("/current-user")
    public Object getUser() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }*/

    /**
     * 当前登录用户信息
     *
     * http://localhost:8080/rest/api/current-user
     *
     * 此接口与 {@link RestApiController#authentication} 返回结果一致
     *
     * @return{
     *     "authorities":[
     *         {
     *             "authority":"admin"
     *         }
     *     ],
     *     "details":{
     *         "remoteAddress":"0:0:0:0:0:0:0:1",
     *         "sessionId":"5933C3D579BC730B1164104421AFEB01"
     *     },
     *     "authenticated":true,
     *     "principal":{
     *         "password":null,
     *         "username":"admin",
     *         "authorities":[
     *             {
     *                 "authority":"admin"
     *             }
     *         ],
     *         "accountNonExpired":true,
     *         "accountNonLocked":true,
     *         "credentialsNonExpired":true,
     *         "enabled":true
     *     },
     *     "credentials":null,
     *     "name":"admin"
     * }
     */
    @GetMapping("/current-user")
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/authentication")
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }

    /**
     * @PreAuthorize 进入方法前的权限验证，需要的权限表达式
     *
     * 根据这个注解所需要的权限，再和当前登录的用户角色所拥有的权限对比，如果用户的角色权限集Set中有这个权限，则放行；没有，拒绝
     */
    @PreAuthorize("hasAuthority('admin')") // 当前登录用户，必须存在 admin 权限才能访问此接口
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    /**
     * @PreAuthorize 进入方法前的权限验证，需要的权限表达式
     *
     * 根据这个注解所需要的权限，再和当前登录的用户角色所拥有的权限对比，如果用户的角色权限集Set中有这个权限，则放行；没有，拒绝
     */
    @PreAuthorize("hasAuthority('member:save')")
    @GetMapping("/add")
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
