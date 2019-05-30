package com.wuzz.demo.core;

import com.wuzz.demo.entity.EntityDemo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
@ApiModel(value = "返回Model")
public class Result<T> implements Serializable{

	//
	@ApiModelProperty("是否成功")
	private boolean success;

	//
	@ApiModelProperty("响应编码")
	private String code ;

	//
	@ApiModelProperty("详细消息")
	private String message;

	//
	@ApiModelProperty("返回数据")
	private T  data ;

	@ApiModelProperty("错误详情")
	private Map<String,String> errs ;

	@ApiModelProperty("错误详情")
	private List<EntityDemo> demo;

	public List<EntityDemo> getDemo() {
		return demo;
	}

	public void setDemo(List<EntityDemo> demo) {
		this.demo = demo;
	}

	public Result(boolean success, String code , String message ) {
		this.success = success;
		this.message = message;
		this.code = code ; 
	}

	public Result(boolean success, String code, String message, T data) {
		this.success = success;
		this.code = code;
		this.message = message;
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
