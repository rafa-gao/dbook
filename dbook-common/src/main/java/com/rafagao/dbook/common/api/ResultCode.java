package com.rafagao.dbook.common.api;

/**
 *
 * 枚举出一些常用的结果码
 * @author rafa gao
 */


public enum ResultCode implements ErrorCode{

    SUCCESS(200,"操作成功"),
    FAILED(500,"操作失败"),
    FORBIDDEN(403, "没有相关权限"),
    VALIDATE_FAILED(404, "参数检验失败")
    ;

    private Long code;
    private String message;


    ResultCode(long code, String message) {
        this.code=code;
        this.message=message;
    }

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
