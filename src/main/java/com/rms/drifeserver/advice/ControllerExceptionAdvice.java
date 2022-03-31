package com.rms.drifeserver.advice;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.rms.drifeserver.domain.common.exception.type.ErrorCode.BAD_GATEWAY;
import static com.rms.drifeserver.domain.common.exception.type.ErrorCode.CONFLICT;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(BaseException.class)
    protected ApiResponse<Object> handleBaseException(BaseException exception) {
        return ApiResponse.error(exception.getErrorCode());
    }
    @ExceptionHandler(Exception.class)
    protected ApiResponse<Object> handleException(Exception exception) {
        System.out.println("unhandled exception");
        exception.printStackTrace();
        return ApiResponse.error(BAD_GATEWAY);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ApiResponse<ErrorCode> handleException(DataIntegrityViolationException exception) {
        System.out.println(exception.getLocalizedMessage()
        );
        return ApiResponse.error(CONFLICT);
    }
}
