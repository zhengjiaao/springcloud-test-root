package com.zja.feign.ims.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 例如：指标的【周期类、单位类、值类】采用是 id + name 形式
 *
 * @Author: zhengja
 * @Date: 2025-09-10 20:25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdName implements Serializable {
    private String id;
    private String name;
}
