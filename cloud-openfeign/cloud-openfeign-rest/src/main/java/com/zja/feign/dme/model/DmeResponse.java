package com.zja.feign.dme.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhengja
 * @Date: 2025-09-19 15:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DmeResponse<T> {
    public static final int RESULT_CODE_SUCCESS = 1000;
    public static final int RESULT_CODE_FAIL = 2000;
    public static final int RESULT_CODE_ERROR = 3000;

    private int code;
    private T data;
    private String message;
    private String status;

    public boolean isSuccess() {
        return RESULT_CODE_SUCCESS == code;
    }

    public boolean isFail() {
        return RESULT_CODE_SUCCESS != code;
    }

    public boolean isEmptyData() {
        return data == null;
    }

}
