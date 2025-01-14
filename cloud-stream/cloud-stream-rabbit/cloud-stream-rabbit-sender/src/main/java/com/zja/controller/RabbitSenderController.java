/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-07-24 16:45
 * @Since:
 */
package com.zja.controller;

import com.zja.rabbit.RabbitSenderExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * 生产者
 *
 * @author: zhengja
 * @since: 2023/07/24 16:45
 */
@Validated
@RestController
@RequestMapping("/rest//Rabbit/Sender")
@Api(tags = {"生产者页面"})
public class RabbitSenderController {

    @Autowired
    RabbitSenderExample service;

    @GetMapping("/String/{str}")
    @ApiOperation("发送String消息")
    public Boolean stringSender(@NotBlank @PathVariable("str") String str) {
//        return service.stringSender(str);
        return Boolean.TRUE;
    }

}