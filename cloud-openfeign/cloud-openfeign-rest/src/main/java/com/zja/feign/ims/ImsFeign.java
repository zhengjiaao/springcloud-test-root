package com.zja.feign.ims;

import com.zja.feign.ims.model.ImsResponse;
import com.zja.feign.ims.model.dto.*;
import com.zja.feign.ims.model.query.IndicatorMetaQuery;
import com.zja.feign.ims.model.query.IndicatorValueUpdate;
import com.zja.feign.ims.model.query.MultiIndicatorValueQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zhengja
 * @Date: 2025-09-10 16:43
 */
@Component
@FeignClient(name = "ims-feign", contextId = "ims-client", url = "${feign.ims.url}")
public interface ImsFeign {

    /**
     * 获取下级子体系列表(仅下级)
     *
     * @param systemId 父体系id
     * @return 子体系列表
     */
    @GetMapping(value = "/ims/admin/v1/system/list/{systemId}/children")
    ImsResponse<List<SystemDTO>> querySubSystemList(@PathVariable(value = "systemId") String systemId);

    /**
     * 获取体系下的所有子目录及指标列表
     *
     * @param systemId 体系id
     * @return 指标列表
     */
    @GetMapping(value = "/ims/admin/v1/system/catalog/indicators/cascade/{systemId}")
    ImsResponse<SystemNodeDTO> queryIndicatorList(@PathVariable(value = "systemId") String systemId,
                                                  @RequestParam(value = "keyword", required = false) String keyword);

    /**
     * 分页查询指标列表
     * <p>
     * 请求示例：
     * 1.若是规划值or实施评估值，则需要版本：<a href="http://192.168.6.242:9090/dgp-ims-web/ims/admin/v1/value/indicators/search/0/10?system=500867&valueType=ghl&version=500064">...</a>
     * 2.若是监测值or对标值，则不需要版本：<a href="http://192.168.6.242:9090/dgp-ims-web/ims/admin/v1/value/indicators/search/0/30?system=500867&valueType=jcl">...</a>
     *
     * @param page      页码，从 0开始，例如：0
     * @param size      每页数量，从 1开始，例如：10
     * @param system    根体系id
     * @param keyword   搜索关键词，例如：指标名称模糊查询等
     * @param valueType 指标类型,例如：ghl（规划值）、sspgl（实施评估值）、jcl（监测值）、dbl（对标值）
     * @param version   版本id
     * @return 指标列表
     */
    @GetMapping(value = "/ims/admin/v1/value/indicators/search/{page}/{size}")
    ImsResponse<IndicatorListPage> queryIndicatorList(@PathVariable(value = "page") Integer page,
                                                      @PathVariable(value = "size") Integer size,
                                                      @RequestParam(value = "system") String system,
                                                      @RequestParam(value = "keyword", required = false) String keyword,
                                                      @RequestParam(value = "valueType") String valueType,
                                                      @RequestParam(value = "version", required = false) String version);

    /**
     * 查询多个指标值（支持查询 规划值、实施评估值、监测值、对标值等）
     *
     * @param multiIndicatorValueQuery 查询参数
     * @return 指标值列表
     */
    @PostMapping("/ims/rest/v1/indicator-value/query/value/multi")
    ImsResponse<List<IndicatorValueData>> queryMultiIndicatorValue(@RequestBody MultiIndicatorValueQuery multiIndicatorValueQuery);

    /**
     * 查询多个指标的元数据信息列表(含属性+扩展属性)
     *
     * @param indicatorMetaQuery 查询参数
     * @return 指标元数据信息列表
     */
    @PostMapping("/ims/rest/v1/indicator/meta/system")
    ImsResponse<List<IndicatorMeta>> queryMultiIndicatorMeta(@RequestBody IndicatorMetaQuery indicatorMetaQuery);

    /**
     * 更新指标监测值
     */
    @PostMapping("/ims/rest/v1/indicator-value/store")
    ImsResponse<Object> updateIndicatorValue(@RequestBody IndicatorValueUpdate indicatorValueUpdate);
}
