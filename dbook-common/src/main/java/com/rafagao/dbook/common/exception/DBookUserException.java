package com.rafagao.dbook.common.exception;

import com.rafagao.dbook.common.api.ErrorCode;

/**
 *
 * @author rafa gao
 */
public class DBookUserException extends RuntimeException{

    private Long code;

    public DBookUserException(String message) {
        super(message);
    }

    public DBookUserException(ErrorCode errorCode){
        super(errorCode.getMessage());
        code=errorCode.getCode();
    }

    public Long getCode() {
        return code;
    }
}
