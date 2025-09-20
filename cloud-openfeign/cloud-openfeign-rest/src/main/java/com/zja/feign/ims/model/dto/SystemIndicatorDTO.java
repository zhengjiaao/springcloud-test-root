package com.zja.feign.ims.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: zhengja
 * @Date: 2025-09-12 14:07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemIndicatorDTO implements Serializable {
    private String id; // 指标id
    private String name; // 指标名称
    private String parentNodeId; // 父节点目录id
    private String parentNodeName; // 父节点目录名称
}
