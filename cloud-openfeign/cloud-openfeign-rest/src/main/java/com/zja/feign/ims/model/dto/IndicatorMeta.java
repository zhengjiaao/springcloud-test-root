package com.zja.feign.ims.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 指标元数据类
 *
 * @Author: zhengja
 * @Date: 2025-09-11 10:46
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndicatorMeta implements Serializable {
    private String id; // 指标id
    private String name; // 指标名称
    private String remark; // 描述
    private Integer sort; // 排序
    private String alias; // 别名
    private IndicatorMetaValues unit; // 单位
    private IndicatorMetaValues period; // 周期
    private String scope;
    private String correlation;
    private String computeCorInd;
    private String tag; // 标签
    private List<IndicatorMetaValues> dims; // 维度
    private Map<String, IndicatorMetaValues> attrs; // 属性
    private String computeModel;
    private String warnModel;
    private Long createTime; // 创建时间
    private Integer relateSystem;
    private Integer relateStandard;
}
