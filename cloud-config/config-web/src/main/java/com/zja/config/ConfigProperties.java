package com.zja.config;

import com.zja.entity.MyAttributes;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-10-28 13:48
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@Data
@Component
@ConfigurationProperties(prefix = "msg.user")
public class ConfigProperties implements Serializable {

    private MyAttributes myAttributes = new MyAttributes();

}
