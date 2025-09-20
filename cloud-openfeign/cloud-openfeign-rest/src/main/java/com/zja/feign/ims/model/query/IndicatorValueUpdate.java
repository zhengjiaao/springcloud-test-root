package com.zja.feign.ims.model.query;

import com.zja.feign.ims.model.dto.ValueData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 指标值更新（监测值）
 *
 * @Author: zhengja
 * @Date: 2025-09-16 16:09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndicatorValueUpdate implements Serializable {
    private List<ValueData> data; // 指标值数据(监测值)
    private String indicatorName; // 指标名称
    private String unit; //  单位
    private String modelCode = "111"; // 模型编码，由于是必须的，给个固定值
    private String taskInstanceCode = "111"; // 任务实例编码，由于是必须的，给个固定值
    private String versionCode = "111"; // 模型版本编码，由于是必须的，给个固定值
}
