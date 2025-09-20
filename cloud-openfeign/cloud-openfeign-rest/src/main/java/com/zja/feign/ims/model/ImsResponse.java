package com.zja.feign.ims.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 调用指标系统的响应结果实体
 *
 * @Author: zhengja
 * @Date: 2025-09-10 16:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImsResponse<T> {

    public static final int RESULT_CODE_SUCCESS = 1000;
    public static final int RESULT_CODE_FAIL = 2000;
    public static final int RESULT_CODE_ERROR = 3000;

    private Integer code;

    private T data;

    private String status;

    private String message;

    public boolean isSuccess() {
        return RESULT_CODE_SUCCESS == code;
    }

    public boolean isFail() {
        return RESULT_CODE_SUCCESS != code;
    }
}
