package com.rms.drifeserver.advice;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.common.exception.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(BaseException.class)
    protected ApiResponse<Object> handleBaseException(BaseException exception) {
        return ApiResponse.error(exception.getErrorCode());
    }
}
