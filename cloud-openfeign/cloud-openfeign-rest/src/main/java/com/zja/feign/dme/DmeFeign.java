package com.zja.feign.dme;

import com.alibaba.fastjson.JSONObject;
import com.zja.feign.dme.model.DmeResponse;
import com.zja.feign.dme.model.dto.ModelInstanceDTO;
import com.zja.feign.dme.model.dto.ModelInstanceResultDTO;
import com.zja.feign.dme.model.dto.ModelInstanceStatusDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 模型服务
 *
 * @Author: zhengja
 * @Date: 2025-09-19 15:14
 */
@Component
@FeignClient(name = "dme-feign", contextId = "dme-client", url = "${feign.dme.url}")
public interface DmeFeign {

    /**
     * 异步运行模型
     *
     * @param modelParams 模型参数
     * @return 模型实例信息
     */
    @GetMapping(value = "/api/tasks/v5/run/modelversions/asyn")
    DmeResponse<List<ModelInstanceDTO>> runModelByAsync(@RequestBody JSONObject modelParams);

    /**
     * 查询模型实例列表
     *
     * @param codes 模型实例code
     * @return 模型实例状态
     */
    @GetMapping(value = "/api/tasks/v1/instances")
    DmeResponse<List<ModelInstanceStatusDTO>> getModelInstanceStatus(@RequestParam("codes") String codes);

    /**
     * 获取模型实例结果
     *
     * @param instanceCode 模型实例code
     * @return 模型实例结果
     */
    @GetMapping(value = "/api/tasks/result/v1/ins/{instanceCode}")
    DmeResponse<ModelInstanceResultDTO> getModelInstanceResult(@PathVariable("instanceCode") String instanceCode);

}