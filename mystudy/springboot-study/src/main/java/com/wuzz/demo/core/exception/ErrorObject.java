package com.wuzz.demo.core.exception;

public class ErrorObject {
	
	private String code ;
	
	private String name ;
	
	private String message ;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorObject() {
	}

	public ErrorObject(String code, String name, String message) {
		this.code = code;
		this.name = name;
		this.message = message;
	}
}
