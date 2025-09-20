package com.zja.feign.ims.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: zhengja
 * @Date: 2025-09-16 16:29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValueData implements Serializable {
    private String regionCode;
    private List<NameValue> values;
    private String year;
}
