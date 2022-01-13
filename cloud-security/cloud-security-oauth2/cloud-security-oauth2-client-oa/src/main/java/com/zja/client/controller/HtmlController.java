/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-11 15:34
 * @Since:
 */
package com.zja.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    /**
     * http://localhost:8081/oa/index
     */
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
