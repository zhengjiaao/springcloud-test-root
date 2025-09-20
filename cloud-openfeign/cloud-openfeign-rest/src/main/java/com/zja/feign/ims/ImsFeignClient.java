package com.zja.feign.ims;

import com.zja.feign.ims.model.ImsResponse;
import com.zja.feign.ims.model.dto.*;
import com.zja.feign.ims.model.query.IndicatorMetaQuery;
import com.zja.feign.ims.model.query.IndicatorValueUpdate;
import com.zja.feign.ims.model.query.MultiIndicatorValueQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhengja
 * @Date: 2025-09-10 17:32
 */
@Component
public class ImsFeignClient {

    @Autowired
    ImsFeign imsFeign;

    /**
     * 查询指标列表（重构结构：目录树-->列表）
     *
     * @param systemId 系统ID
     * @param keyword  关键字（指标名称）
     * @return 指标列表
     */
    public List<SystemIndicatorDTO> queryIndicatorList(String systemId, String keyword) {
        ImsResponse<SystemNodeDTO> response = imsFeign.queryIndicatorList(systemId, keyword);

        List<SystemIndicatorDTO> indicators = new ArrayList<>();

        // 检查响应是否失败
        if (response.isFail()) {
            throw new RuntimeException("查询指标列表失败：" + response.getMessage());
        }

        SystemNodeDTO rootNode = response.getData();

        // 递归收集所有指标节点
        collectIndicators(rootNode, null, indicators);

        return indicators;
    }


    /**
     * 递归收集所有指标节点，并添加父节点信息
     *
     * @param currentNode 当前处理的节点
     * @param parentNode  父节点
     * @param indicators  指标集合
     */
    private void collectIndicators(SystemNodeDTO currentNode, SystemNodeDTO parentNode, List<SystemIndicatorDTO> indicators) {
        // 检查当前节点是否为空
        if (currentNode == null) {
            return;
        }

        // 检查当前节点是否为指标节点
        if (Boolean.TRUE.equals(currentNode.getIndicator())) {
            // 构建指标DTO并添加到列表，同时检查父节点不为空
            if (parentNode != null) {
                indicators.add(SystemIndicatorDTO.builder()
                        .id(currentNode.getId())
                        .name(currentNode.getName())
                        .parentNodeId(parentNode.getId())
                        .parentNodeName(parentNode.getName())
                        .build());
            } else {
                // 如果父节点为空，则不添加父节点信息
                indicators.add(SystemIndicatorDTO.builder()
                        .id(currentNode.getId())
                        .name(currentNode.getName())
                        .build());
            }
        }

        // 如果有子节点，则递归处理
        if (currentNode.getChildren() != null) {
            for (SystemNodeDTO child : currentNode.getChildren()) {
                // 传递当前节点作为子节点的父节点
                collectIndicators(child, currentNode, indicators);
            }
        }
    }

    public ImsResponse<List<IndicatorValueData>> queryMultiIndicatorValue(MultiIndicatorValueQuery multiIndicatorValueQuery) {
        return imsFeign.queryMultiIndicatorValue(multiIndicatorValueQuery);
    }

    public ImsResponse<List<IndicatorMeta>> queryMultiIndicatorMeta(IndicatorMetaQuery indicatorMetaQuery) {
        return imsFeign.queryMultiIndicatorMeta(indicatorMetaQuery);
    }


    /**
     * 批量更新指标值
     *
     * @param indicatorValueUpdateList 指标值更新列表（仅支持监测值更新）
     * @return 更新结果
     */
    public void batchUpdateIndicatorValue(List<IndicatorValueUpdate> indicatorValueUpdateList) {
        if (CollectionUtils.isEmpty(indicatorValueUpdateList)) {
            return;
        }

        for (IndicatorValueUpdate valueUpdate : indicatorValueUpdateList) {
            imsFeign.updateIndicatorValue(valueUpdate);
        }
    }

}
