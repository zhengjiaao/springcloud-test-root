package com.zja.feign.ims.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 例如：指标的【检测值】采用是 name + value 形式
 *
 * @Author: zhengja
 * @Date: 2025-09-16 16:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NameValue implements Serializable {
    private String name;
    private String value;
}
