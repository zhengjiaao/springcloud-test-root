/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-12 10:40
 * @Since:
 */
package com.zja.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping
public class IndexController {

    @Value("${spring.application.name}")
    private String name;

    @Value("${spring.application.version}")
    private String version;

    @Value("${platform.oa.url}")
    public String oaProfileUri;

    @Value("${platform.qq.url}")
    public String qqProfileUri;

    /**
     * 请求登录 http://localhost:8080/login
     */
    @GetMapping("/")
    public String index(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("name", name);
        model.addAttribute("version", version);
        model.addAttribute("oaProfileUri", oaProfileUri);
        model.addAttribute("qqProfileUri", qqProfileUri);

        return "index";
    }

}
