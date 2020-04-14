package com.wuzz.demo.core.exception;


/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/11
 * Time: 14:41
 * Description 描述:
 */

public class BusinessException extends RuntimeException {

    //    private ErrorObject error ;
    // 枚举类型错误
    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BusinessException(String msg) {
        super(msg);
        this.message = msg;
    }

    //    public BusinessException(ErrorObject error) {
//        super(error.getMessage()) ;
//        this.error = error ;
//    }
    // 枚举类型错误
    public BusinessException(CommonErrorEnum commonErrorEnum) {
        super(commonErrorEnum.getMessage());
        this.code = commonErrorEnum.getCode();
        this.message = commonErrorEnum.getMessage();
    }

//    public ErrorObject getError() {
//        return error;
//    }
//
//    public void setError(ErrorObject error) {
//        this.error = error;
//    }

}
