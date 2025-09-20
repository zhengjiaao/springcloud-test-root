package com.zja.feign.dme;

import com.alibaba.fastjson.JSONObject;
import com.zja.feign.dme.model.DmeResponse;
import com.zja.feign.dme.model.dto.ModelInstanceDTO;
import com.zja.feign.dme.model.dto.ModelInstanceStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: zhengja
 * @Date: 2025-09-19 15:56
 */
@Component
@RequiredArgsConstructor
public class DmeFeignClient {

    private final DmeFeign dmeFeign;

    // 运行模型
    public ModelInstanceDTO runModel(JSONObject modelParams) {
        DmeResponse<List<ModelInstanceDTO>> response = dmeFeign.runModelByAsync(modelParams);

        // 检查响应是否失败
        if (response.isFail()) {
            throw new RuntimeException("运行模型失败：" + response.getMessage());
        }

        List<ModelInstanceDTO> dtoList = response.getData();
        if (CollectionUtils.isEmpty(dtoList)) {
            throw new RuntimeException("运行模型失败-获取不到模型实例数据：" + response.getMessage());
        }

        // 获取第一个模型实例
        return dtoList.get(0);
    }

    // 获取模型实例状态
    public ModelInstanceStatusDTO getModelInstanceStatus(String modelInstanceCode) {
        DmeResponse<List<ModelInstanceStatusDTO>> response = dmeFeign.getModelInstanceStatus(modelInstanceCode);

        if (response.isFail()) {
            throw new RuntimeException("获取模型实例失败：" + response.getMessage());
        }

        List<ModelInstanceStatusDTO> dtoList = response.getData();
        if (CollectionUtils.isEmpty(dtoList)) {
            throw new RuntimeException("获取模型实例失败-获取不到模型实例数据：" + response.getMessage());
        }

        // 获取第一个模型实例
        return dtoList.get(0);
    }

}
