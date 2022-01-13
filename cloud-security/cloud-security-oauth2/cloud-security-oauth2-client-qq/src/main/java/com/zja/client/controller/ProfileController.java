/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-12 10:40
 * @Since:
 */
package com.zja.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ProfileController {

    @Value("${spring.application.name}")
    private String name;

    @Value("${spring.application.version}")
    private String version;

    @Value("${oa.profile-uri}")
    public String oaProfileUri;

    /**
     *  http://localhost:8082/qq/profile
     */
    @GetMapping("/profile")
    public String index(Model model) {
        model.addAttribute("name", name);
        model.addAttribute("version", version);
        model.addAttribute("oaProfileUri", oaProfileUri);

        return "profile";
    }
}
