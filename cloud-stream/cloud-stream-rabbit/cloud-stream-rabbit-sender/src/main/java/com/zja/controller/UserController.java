/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-07-24 17:09
 * @Since:
 */
package com.zja.controller;

import com.zja.model.dto.UserDTO;
import com.zja.model.request.UserRequest;
import com.zja.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 用户管理
 *
 * @author: zhengja
 * @since: 2023/07/24 17:09
 */
@Validated
@RestController
@RequestMapping("/rest/user")
@Api(tags = {"用户管理页面"})
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/create")
    @ApiOperation("创建用户管理")
    public UserDTO create(@Valid @RequestBody UserRequest request) {
        return service.save(request);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除用户管理")
    public void deleteById(@NotBlank @PathVariable("id") String id) {
        service.deleteById(id);
    }

}