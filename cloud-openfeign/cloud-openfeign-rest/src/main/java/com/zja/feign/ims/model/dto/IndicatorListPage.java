package com.zja.feign.ims.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据类
 *
 * @Author: zhengja
 * @Date: 2025-09-11 10:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndicatorListPage implements Serializable {
    private Integer index;
    private Integer size;
    private Integer length;
    private Integer pages;
    private Integer count;
    private Boolean first;
    private Boolean last;
    private Boolean hasNext;
    private Boolean hasPrevious;
    private List<IndicatorItem> data;
}
