package com.zja.feign.ims.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 指标项类
 *
 * @Author: zhengja
 * @Date: 2025-09-11 10:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndicatorItem implements Serializable {
    private String id; //  指标项id
    private String name; //  指标项名称
    private String remark; //  备注
    private Integer sort; //  排序
    private IdName unit; //  单位类
    private List<IdName> period; //  周期类
    private Boolean hasValue; //  是否有指标值

    public String getUnitName() {
        return unit == null ? null : unit.getName();
    }
}
