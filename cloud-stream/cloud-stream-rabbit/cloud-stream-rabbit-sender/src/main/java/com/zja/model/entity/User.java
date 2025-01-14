/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-07-24 17:15
 * @Since:
 */
package com.zja.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 实体类
 *
 * @author: zhengja
 * @since: 2023/07/24 17:15
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "test_user")
public class User {

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

}