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
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * http://localhost:19000/swagger-ui/index.html#/
 * https://localhost:19001/swagger-ui/index.html#/
 */
@Api(value = "提供远程-文件上传测试接口")
@RestController
@RequestMapping
public class RemoteFileController {

    @PostMapping(value = "/post/upload/v1")
    @ApiOperation(value = "post-上传单文件", notes = "返回 true")
    public Object postFile(@ApiParam("上传文件") @RequestPart(value = "file") MultipartFile file) {
        System.out.println("上传文件：" + file.getOriginalFilename() + "  大小：" + file.getSize());
        return true;
    }

    @PostMapping(value = "/post/upload/v2")
    @ApiOperation(value = "post-上传多文件", notes = "返回 true")
    public Object postFile(@RequestPart(value = "files") MultipartFile[] files) {
        if (files.length <= 0) {
            return "未选择要上传的文件！";
        }
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            System.out.println("上传文件：" + file.getOriginalFilename() + "  大小：" + file.getSize());
        }
        return true;
    }

    @PostMapping(value = "/post/upload/v3")
    @ApiOperation(value = "post-上传单文件和字符串", notes = "返回 true")
    public Object postFile(@ApiParam("上传文件") @RequestPart(value = "file") MultipartFile file,
                           @ApiParam("新文件名称") @RequestParam String filename) {
        System.out.println("上传文件：" + filename + "  大小：" + file.getSize());
        return true;
    }

    @PostMapping(value = "/post/upload/v4")
    @ApiOperation(value = "post-上传单文件和json对象", notes = "返回 true")
    public Object postFile(@ApiParam("上传文件") @RequestPart(value = "file") MultipartFile file,
                           @ApiParam("对象") @RequestBody UserDTO userDTO) {
        System.out.println("上传文件：" + file.getOriginalFilename() + "  大小：" + file.getSize());
        System.out.println(userDTO.toString());
        return true;
    }

    @PostMapping(value = "/post/upload/v5")
    @ApiOperation(value = "post-上传多文件和字符串", notes = "返回 true")
    public Object postFile(@RequestPart(value = "files") MultipartFile[] files,
                           @ApiParam("新文件名称") @RequestParam String filename) {
        if (files.length <= 0) {
            return "未选择要上传的文件！";
        }
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            System.out.println("上传文件：" + file.getOriginalFilename() + "  大小：" + file.getSize());
        }
        System.out.println(filename);
        return true;
    }

    @PostMapping(value = "/post/upload/v6")
    @ApiOperation(value = "post-上传多文件和字符串", notes = "返回 true")
    public Object postFile(@RequestPart(value = "files") MultipartFile[] files,
                           @ApiParam("对象") UserDTO userDTO) {
        if (files.length <= 0) {
            return "未选择要上传的文件！";
        }
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            System.out.println("上传文件：" + file.getOriginalFilename() + "  大小：" + file.getSize());
        }
        System.out.println(userDTO.toString());
        return true;
    }
}
