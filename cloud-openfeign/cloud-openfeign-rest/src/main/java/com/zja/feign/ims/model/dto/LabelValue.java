package com.zja.feign.ims.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 标签值类
 *
 * @Author: zhengja
 * @Date: 2025-09-10 20:25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabelValue implements Serializable {
    private String label;
    private String value;
}
