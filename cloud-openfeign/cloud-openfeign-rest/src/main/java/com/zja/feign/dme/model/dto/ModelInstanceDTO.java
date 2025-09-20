package com.zja.feign.dme.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 模型运行结果
 *
 * @Author: zhengja
 * @Date: 2025-09-19 15:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelInstanceDTO implements Serializable {
    private long createTime;
    private long lastTime;
    private String modelCode;
    private String modelName;
    private String status; // 模型运行状态: waiting、running、success、fail
    private String taskCode;
    private String taskInstanceCode; // 模型实例code
    private String versionCode; // 模型版本code
    private String versionName; // 模型版本名称
}
