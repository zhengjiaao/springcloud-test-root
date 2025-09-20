package com.zja.feign.ims.model.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 多指标值查询参数类
 * <p>
 *{
 *     "indicatorId": [
 *         "501152","501151"
 *     ],
 *     "region": [
 *         "sfq1"
 *     ],
 *     "system": "总规实施监测评估",
 *     "time": "",
 *     "type": "sspgl",
 *     "version": "实施评估监测"
 * }
 * </p>
 *
 * @Author: zhengja
 * @Date: 2025-09-11 11:15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultiIndicatorValueQuery implements Serializable {
    private List<String> indicatorId; // 指标ID
    private List<String> region; // 行政区ID
    private String system; // 体系名称
    private String time; // 时间区间，例如：2025、2022/、/2025 、2022/2025 等
    private String type; // 指标值类型/体系类型
    private String version;  // 体系版本名称
}
