package com.zja.feign.ims.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 指标元数据值列表，例如：指标元数据的【Attr 属性类、Dim维度类、UnitMeta单位元数据类】
 *
 * @Author: zhengja
 * @Date: 2025-09-11 10:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndicatorMetaValues implements Serializable {
    private List<IdName> values;
    private String config;

    public IdName getIdName() {
        if (values == null || values.isEmpty()) {
            return null;
        }
        return values.get(0);
    }

    public String getName() {
        if (values == null || values.isEmpty()) {
            return null;
        }
        IdName idName = values.get(0);
        if (idName == null) {
            return null;
        }
        return idName.getName() != null ? idName.getName() : "";
    }

}
