package com.zja.predicates;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-11-03 10:28
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@Data
public class TimeBetweenConfig implements Serializable {
    /**
     * 开始时间
     */
    private LocalTime start;

    /**
     * 结束时间
     */
    private LocalTime end;
}
