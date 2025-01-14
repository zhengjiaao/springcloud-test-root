/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-07-24 16:49
 * @Since:
 */
package com.zja.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhengja
 * @since: 2023/07/24 16:49
 */
@Data
@ApiModel("UserDTO")
public class UserDTO implements Serializable {

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    public UserDTO(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
}