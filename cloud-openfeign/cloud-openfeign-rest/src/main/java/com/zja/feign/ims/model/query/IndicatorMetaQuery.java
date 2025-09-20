package com.zja.feign.ims.model.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 指标元数据查询参数类
 *
 * <p>
 *{
 *   "indicatorIds": [
 *     "501152","501151"
 *   ],
 *   "system": "" // 500867（可选地，非必填）
 * }
 * </p>
 *
 * @Author: zhengja
 * @Date: 2025-09-11 10:44
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndicatorMetaQuery implements Serializable {
    private List<String> indicatorIds; // 指标id集合
    private String system; // 系统id
}
