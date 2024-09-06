/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-22 16:57
 * @Since:
 */
package com.zja.controller;

import com.zja.feign.RestFeignClient;
import com.zja.feign.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * http://localhost:18000/swagger-ui/index.html#/
 *
 * Feign默认：支持 http、https 协议
 */
@Api(value = "Feign 常规http测试")
@RestController
@RequestMapping("/feign/rest")
public class RestFeignClientController {

    @Autowired
    private RestFeignClient restFeignClient;

    //get

    @GetMapping(value = "/get")
    @ApiOperation(value = "get-无参数", notes = "返回字符串")
    public Object get() {
        return restFeignClient.get();
    }

    @GetMapping(value = "/get/{path}")
    @ApiOperation(value = "get-路径参数")
    public Object getPath1(@PathVariable("path") String path) {
        return restFeignClient.getPath1(path);
    }

    @GetMapping(value = "/get/param")
    @ApiOperation(value = "get-拼接参数")
    public Object getPath2(@RequestParam("path") String path) {
        return restFeignClient.getPath2(path);
    }

    @Deprecated // 无法正确接收参数
    @GetMapping(value = "/get/param/v2")
    @ApiOperation(value = "get-拼接参数", notes = "对象属性参数，相当于 @RequestParam(required = false) 属性名")
    public Object getObject(UserDTO userDTO) {
        return restFeignClient.getParam2(userDTO);
    }

    @GetMapping(value = "/get/param/v3")
    @ApiOperation(value = "get-拼接参数", notes = "对象属性参数，相当于 @RequestParam(required = false) 属性名")
    public Object getObject3(UserDTO userDTO) {
        return restFeignClient.getParam3(userDTO);
    }

    @GetMapping(value = "/get/object/v1")
    @ApiOperation(value = "get-无参数", notes = "返回对象")
    public Object getObject() {
        return restFeignClient.getObject();
    }

    @GetMapping(value = "/get/object/list/v1")
    @ApiOperation(value = "get-无参数", notes = "返回List")
    public Object getObjectList() {
        return restFeignClient.getObjectList();
    }

    //post

    @PostMapping(value = "/post")
    @ApiOperation(value = "post-无参数", notes = "返回字符串")
    public Object post() {
        return restFeignClient.post();
    }

    /*@PostMapping(value = "/post/param")
    @ApiOperation(value = "post-多个参数", notes = "返回字符串")
    public Object postObject(@ApiParam(value = "传参值：name") @RequestParam String name,
                             @ApiParam(value = "传参值：age") @RequestParam String age) {
        return restFeignClient.postObject(name, age);
    }*/

    @PostMapping(value = "/post/object/v1")
    @ApiOperation(value = "post-无参数", notes = "返回对象")
    public Object postObject() {
        return restFeignClient.postObject();
    }

    @PostMapping(value = "/post/object/v2")
    @ApiOperation(value = "post-对象参数", notes = "返回对象")
    public Object postObject(@RequestBody UserDTO userDto) {
        return restFeignClient.postObject(userDto);
    }

    //put

    @PutMapping(value = "/put")
    @ApiOperation(value = "put-无参数", notes = "返回字符串")
    public Object put() {
        return restFeignClient.put();
    }

    /*@PutMapping(value = "/put/param")
    @ApiOperation(value = "put-参数", notes = "返回字符串")
    public Object putParam(@RequestParam String name) {
        return restFeignClient.putParam(name);
    }
*/
    @PutMapping(value = "/put/object/v1")
    @ApiOperation(value = "put-无参数", notes = "返回对象")
    public Object putObject() {
        return restFeignClient.postObject();
    }

    @PutMapping(value = "/put/object/v2")
    @ApiOperation(value = "put-对象参数", notes = "返回对象")
    public UserDTO putObject(@RequestBody UserDTO userDto) {
        return restFeignClient.putObject(userDto);
    }

    //delete

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "delete-无参数", notes = "返回字符串")
    public Object delete() {
        return restFeignClient.delete();
    }

    @DeleteMapping(value = "/delete/{path}")
    @ApiOperation(value = "delete-路径参数")
    public Object deletePath1(@PathVariable("path") String path) {
        return restFeignClient.deletePath1(path);
    }

  /*  @DeleteMapping(value = "/delete/param")
    @ApiOperation(value = "delete-拼接参数")
    public Object deletePath2(@RequestParam("path") String path) {
        return restFeignClient.deletePath2(path);
    }
*/
    @DeleteMapping(value = "/delete/object/v1")
    @ApiOperation(value = "delete-无参数", notes = "返回对象")
    public Object deleteObject() {
        return restFeignClient.deleteObject();
    }

  /*  @DeleteMapping(value = "/delete/object/v2")
    @ApiOperation(value = "delete-传参数-对象", notes = "返回对象")
    public Object deleteObject(UserDTO userDto) {
        return restFeignClient.deleteObject(userDto);
    }*/

}
