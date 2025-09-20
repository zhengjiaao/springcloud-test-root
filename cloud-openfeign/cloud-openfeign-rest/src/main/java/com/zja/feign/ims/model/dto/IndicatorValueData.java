package com.zja.feign.ims.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * 通用指标值数据类
 *
 * @Author: zhengja
 * @Date: 2025-09-11 11:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndicatorValueData implements Serializable {

    // 通用字段
    private LabelValue unit;        // 单位
    private LabelValue r;           // 区域
    private LabelValue v;           // 版本
    private LabelValue i;           // 指标
    private Long updateTime;        // 更新时间
    private String id;              // ID

    // 规划值特有字段
    private LabelValue jqz;         // 基期值
    private LabelValue yqmbz;       // 远期目标值
    private LabelValue jqmbz;       // 近期目标值
    private LabelValue mbz;         // 目标值
    private LabelValue ghzsm;       // 规划值说明

    // 评估值特有字段
    private LabelValue jqn;         // 基期年
    private LabelValue pgzsm;       // 评估值说明
    private LabelValue ndmbz;       // 年度目标值
    private LabelValue mbn;         // 目标年
    private LabelValue ndmbn;       // 年度目标年
    private Map<String, LabelValue> xzz; // 现状值（按年份）

    // 监测值特有字段
    private LabelValue jcz;         // 监测值
    private LabelValue t;           // 时间（监测）
}
