package com.wuzz.demo.exception;



/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/11
 * Time: 14:41
 * Description 描述:
 */

public class BusinessException extends RuntimeException{

    private ErrorObject error ;
    // 枚举类型错误
    private CommonErrorEnum commonErrorEnum;

    public BusinessException(String msg) {
        super(msg) ;
    }

    public BusinessException(ErrorObject error) {
        //super(error.getMessage()) ;
        this.error = error ;
    }
    // 枚举类型错误
    public BusinessException(CommonErrorEnum commonErrorEnum) {
        //super(error.getMessage()) ;
        this.commonErrorEnum = commonErrorEnum ;
    }

    public ErrorObject getError() {
        return error;
    }

    public void setError(ErrorObject error) {
        this.error = error;
    }

    public CommonErrorEnum getCommonErrorEnum() {
        return commonErrorEnum;
    }

    public void setCommonErrorEnum(CommonErrorEnum commonErrorEnum) {
        this.commonErrorEnum = commonErrorEnum;
    }
}
