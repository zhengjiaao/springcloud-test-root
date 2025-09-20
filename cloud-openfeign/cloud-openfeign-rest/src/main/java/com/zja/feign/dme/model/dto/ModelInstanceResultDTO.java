package com.zja.feign.dme.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: zhengja
 * @Date: 2025-09-19 15:49
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelInstanceResultDTO implements Serializable {
    private List<ResultSetItem> resultSet;
    private String stepCode;
    private String stepName;
}
