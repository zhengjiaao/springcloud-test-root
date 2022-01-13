/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-11 10:17
 * @Since:
 */
package com.zja.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登陆、退出控制器
 */
@Slf4j
@Controller
public class LoginController {

    /**
     * http://localhost:8080/login
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * http://localhost:8080/oauth/authorize?response_type=code&client_id=client_id_oa&redirect_uri=http://www.baidu.com&state=123
     */
    @GetMapping("/oauth/login")
    public String oauth_login() {
        return "oauth_login";
    }

    /**
     * 授权认证 确认页面
     * http://localhost:8080/oauth/confirm_access
     */
    @GetMapping("/oauth/confirm_access")
    public String index() {
        return "oauth_approval";
    }
}
