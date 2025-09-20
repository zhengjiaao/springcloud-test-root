package com.zja.feign.ims.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 指标体系信息
 *
 * @Author: zhengja
 * @Date: 2025-09-12 13:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemDTO implements Serializable {
    private String id; // 指标体系id
    private String name; // 指标体系名称
    private String parent; // 父级id
    private String remark; // 描述
    private Integer sort; // 排序
    private Long createTime; // 创建时间
    private Integer count; // 子指标体系数量
    private Integer indicatorCount; // 指标数量
    private Boolean hasIndicator; // 是否有指标
    private Integer currentIndicatorCount; // 当前指标数量
}
