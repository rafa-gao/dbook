package com.rafagao.dbook.common.api;

/**
 * 通用返回对象
 * @author rafa gao
 */


public class CommonResult<T> {

    //返回的数据
    private T data;
    //响应消息
    private String message;
    //响应码
    private long code;



    public CommonResult(T data, String message, long code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    /**
     * 携带默认Message的成功返回结果
     *
     * @param data 返回的数据
     * @return 返回的结果
     */
    public static <T> CommonResult<T> success(T data){
        return common(data,null,ResultCode.SUCCESS);
    }

    /**
     * 携带自定义Message的成功返回结果
     *
     * @param data 返回的数据
     * @return 返回的结果
     */
    public static <T> CommonResult<T> success(T data,String message){
        return common(data,message,ResultCode.SUCCESS);
    }


    /**
     * 携带自定义Message的失败返回结果
     *
     * @param data 返回的数据
     * @return 返回的结果
     */
    public static <T> CommonResult<T> failed(T data,String message){
        return common(data,message,ResultCode.FAILED);
    }

    /**
     * 携带默认Message的失败返回结果
     *ed
     * @param data 返回的数据
     * @return 返回的结果
     */
    public static <T> CommonResult<T> failed(T data){
        return common(data,null,ResultCode.FAILED);
    }

    public static <T> CommonResult<T> forbidden(T data){
        return common(data,null,ResultCode.FORBIDDEN);
    }

    public static <T> CommonResult<T> forbidden(T data ,String message){
        return common(data,message,ResultCode.FAILED);
    }

    public static <T> CommonResult<T> validateFailed(T data){
        return common(data,null,ResultCode.VALIDATE_FAILED);
    }


    public static <T> CommonResult<T> validatedFailed(T data,String message){
        return common(data,message,ResultCode.VALIDATE_FAILED);
    }

    /**
     * 携带自定义Message的成功返回结果
     *
     * @param data 返回的数据
     * @return 返回的结果
     */
    private static <T> CommonResult<T> common(T data,String cusMessage,ErrorCode errorCode){
        if(cusMessage==null){
            return new CommonResult<>(data,errorCode.getMessage(),errorCode.getCode());
        }else {
            return new CommonResult<>(data,cusMessage,errorCode.getCode());
        }
    }

}
