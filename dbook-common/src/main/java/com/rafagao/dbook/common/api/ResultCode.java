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
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),

    // 3开头为用户
    DUPLICATE_USERNAME(302,"该用户已存在"),
    REGISTER_FAILED(303,"用户注册失败"),
    USER_NOT_EXIT(304,"用户不存在"),
    PASSEORD_ERROR(305,"密码错误")
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
