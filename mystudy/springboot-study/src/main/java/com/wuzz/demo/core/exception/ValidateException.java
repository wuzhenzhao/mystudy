package com.wuzz.demo.core.exception;

import java.util.List;

public class ValidateException extends RuntimeException {
	
	private List<String> errs ;
	
	public ValidateException(List<String> errs) {
		super() ;
		this.errs = errs ;
	}
	
	public List<String> getErrs(){
		return this.errs ;
	}

	public void setErrs(List<String> errs) {
		this.errs = errs;
	}
}
