package com.zja.feign.dme.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: zhengja
 * @Date: 2025-09-19 15:42
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelInstanceStatusDTO implements Serializable {
    private Long taskId;
    private String taskCode;
    private String sysCode; // 模型实例code
    private String status; // 模型实例运行状态：waiting、running、success、fail
    private Long createTime;
    private Long lastTime;
}
