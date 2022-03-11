package com.rms.drifeserver.domain.common.exception;

import com.rms.drifeserver.domain.common.exception.type.ErrorCode;

public class BaseException extends Exception{
    private ErrorCode errorCode;
    public BaseException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode=errorCode;
    }
    public ErrorCode getErrorCode(){
        return this.errorCode;
    }
}
