/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-21 14:16
 * @Since:
 */
package com.zja.controller;

import com.zja.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * http://localhost:19000/swagger-ui/index.html#/
 * https://localhost:19001/swagger-ui/index.html#/
 */
@Api("提供远程-Rest测试接口")
@RestController
@RequestMapping
public class RemoteRestController {

    //get

    @GetMapping(value = "/get")
    @ApiOperation(value = "get-无参数", notes = "返回字符串")
    public String get() {

        //提供服务降级测试 例如：hystrix

        //模拟出异常
        //int age = 10/0;

        //模拟请求超时
        /*try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        return "get 请求成功！";
    }

    @GetMapping(value = "/get/{path}")
    @ApiOperation(value = "get-路径参数")
    public Object getPath1(@PathVariable("path") String path) {
        return "get 请求成功: " + path;
    }

    @GetMapping(value = "/get/param/v1")
    @ApiOperation(value = "get-拼接参数")
    public Object getPath2(@RequestParam("path") String path) {
        return "get 请求成功: " + path;
    }

    @GetMapping(value = "/get/object/v1")
    @ApiOperation(value = "get-无参数", notes = "返回对象")
    public Object getObject() {
        return getUserDto();
    }

    @GetMapping(value = "/get/object/list/v1")
    @ApiOperation(value = "get-无参数", notes = "返回List")
    public Object getObjectList() {
        List list = new ArrayList();
        list.add(getUserDto());
        list.add(getUserDto());
        list.add(getUserDto());
        return list;
    }

    //post

    @PostMapping(value = "/post")
    @ApiOperation(value = "post-无参数", notes = "返回字符串")
    public Object post() {
        return "post 请求成功！";
    }

    @PostMapping(value = "/post/param")
    @ApiOperation(value = "post-多个参数", notes = "返回字符串")
    public Object postObject(@ApiParam(value = "传参值：name") @RequestParam String name,
                             @ApiParam(value = "传参值：age") @RequestParam String age) {
        String result = "名字：" + name + "  ，年龄：" + age;
        return result;
    }

    @PostMapping(value = "/post/object/v1")
    @ApiOperation(value = "post-无参数", notes = "返回对象")
    public Object postObject() {
        return getUserDto();
    }

    @PostMapping(value = "/post/object/v2")
    @ApiOperation(value = "post-对象参数", notes = "返回对象")
    public Object postObject(@RequestBody UserDTO userDto) {
        return userDto;
    }

    //put

    @PutMapping(value = "/put")
    @ApiOperation(value = "put-无参数", notes = "返回字符串")
    public Object put() {
        return "put 请求成功！";
    }

    @PutMapping(value = "/put/param")
    @ApiOperation(value = "put-参数", notes = "返回字符串")
    public Object putParam(@RequestParam String name) {
        return "put 请求成功：" + name;
    }

    @PutMapping(value = "/put/object/v1")
    @ApiOperation(value = "put-无参数", notes = "返回对象")
    public UserDTO putObject() {
        return getUserDto();
    }

    @PutMapping(value = "/put/object/v2")
    @ApiOperation(value = "put-对象参数", notes = "返回对象")
    public UserDTO putObject(@RequestBody UserDTO userDto) {
        return userDto;
    }

    //delete

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "delete-无参数", notes = "返回字符串")
    public Object delete() {
        return "delete 请求成功！";
    }

    @DeleteMapping(value = "/delete/{path}")
    @ApiOperation(value = "delete-路径参数")
    public Object deletePath1(@PathVariable("path") String path) {
        return "delete 请求成功: " + path;
    }

    @DeleteMapping(value = "/delete/param")
    @ApiOperation(value = "delete-拼接参数")
    public Object deletePath2(@RequestParam("path") String path) {
        return "delete 请求成功: " + path;
    }

    @DeleteMapping(value = "/delete/object/v1")
    @ApiOperation(value = "delete-无参数", notes = "返回对象")
    public Object deleteObject() {
        return getUserDto();
    }


    /**
     * 获取userdto 信息
     * @return
     */
    private UserDTO getUserDto() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId("1");
        userDTO.setName("zhengja");
        userDTO.setDate(new Date());
        return userDTO;
    }

}
