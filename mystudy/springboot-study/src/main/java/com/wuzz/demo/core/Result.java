package com.wuzz.demo.core;

import java.io.Serializable;
import java.util.Map;

public class Result<T> implements Serializable{

	//
	private boolean success;

	//
	private String code ;

	//
	private String message;

	//
	private T  data ;
	
	private Map<String,String> errs ;
	
	
	public Result(boolean success, String code ,String message ) {
		this.success = success;
		this.message = message;
		this.code = code ; 
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
