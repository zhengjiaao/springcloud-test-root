package com.zja.feign.ims.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 指标体系节点
 *
 * @Author: zhengja
 * @Date: 2025-09-12 13:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemNodeDTO implements Serializable {
    private String id; // 目录节点id
    private String name; // 目录节点名称
    private Boolean system; // 是否是系统
    private Boolean catalog; // 是否是目录
    private Boolean indicator; // 是否是指标
    private List<SystemNodeDTO> children; // 指标节点(这个级别才可能是指标列表)
}
