package com.zja.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-11-02 12:44
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@Data
public class User implements Serializable {
    private String name;
    private int age;
}
