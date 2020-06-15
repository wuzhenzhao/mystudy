package com.wuzz.demo.core;

import com.fasterxml.jackson.annotation.JsonView;
import com.wuzz.demo.core.exception.CommonErrorEnum;

import java.io.Serializable;
import java.util.Map;

public class Result<T> implements Serializable {

    public interface BaseJsonView{}

    public interface SimpleJsonView extends  BaseJsonView{}

    //
    @JsonView(BaseJsonView.class)
    private boolean success;

    //
    @JsonView(BaseJsonView.class)
    private String code;

    //
    @JsonView(BaseJsonView.class)
    private String message;

    //
    @JsonView(BaseJsonView.class)
    private T data;

    private Map<String, String> errs;

    public Result() {
    }

    public Result(boolean success, String code, String message) {
        this.success = success;
        this.message = message;
        this.code = code;
    }

    public Result(boolean success, CommonErrorEnum commonErrorEnum) {
        this.success = success;
        this.message = commonErrorEnum.getMessage();
        this.code = commonErrorEnum.getCode();
    }

    public Result(boolean success, T data) {
        this.success = success;
        this.code = "001";
        this.message = "success";
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, String> getErrs() {
        return errs;
    }

    public void setErrs(Map<String, String> errs) {
        this.errs = errs;
    }
}
