package com.zja.feign.dme.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: zhengja
 * @Date: 2025-09-19 15:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultSetItem implements Serializable {
    private String alias;
    private Integer dataType;
    private String dataTypeCode;
    private String dataTypeDesc;
    private String name;
    private Object originValue;
    private String value;
}
