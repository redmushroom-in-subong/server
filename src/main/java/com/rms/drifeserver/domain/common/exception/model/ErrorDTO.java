package com.rms.drifeserver.domain.common.exception.model;

import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDTO {
    private String code;
    private String message;

    public static ErrorDTO of(ErrorCode errorCode) {
        return new ErrorDTO(errorCode.getCode(), errorCode.getMessage());
    }

}
