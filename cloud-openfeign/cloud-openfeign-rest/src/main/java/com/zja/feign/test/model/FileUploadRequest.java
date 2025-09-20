package com.zja.feign.test.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * 文档上传时参数
 * @Author: zhengja
 * @Date: 2024-09-05 13:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("FileUploadRequest 文件上传信息")
public class FileUploadRequest implements Serializable {

    @ApiModelProperty("必传项，上传文件")
    private MultipartFile file;
    // private MultipartFile[] files;

    @ApiModelProperty(value = "用户id")
    private String id;
    @ApiModelProperty(value = "用户名")
    private String name;
    @ApiModelProperty(value = "时间")
    private Date date;
}
