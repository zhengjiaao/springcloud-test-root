package com.zja.feign.test.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "用户信息")
public class UserDTO implements Serializable{
    @ApiModelProperty(value = "用户id")
    private String id;
    @ApiModelProperty(value = "用户名")
    private String name;
    @ApiModelProperty(value = "时间")
    private Date date;
}
