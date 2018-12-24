package com.ksh.reqrep;

import java.io.Serializable;

/**
KSHITIJ
Dec 24, 2018
**/
public class ValError implements Serializable {

	private static final long serialVersionUID = 1L;
	private ErrorCode code;
	private String field;
	private String message;
	
	public ErrorCode getCode() {
		return code;
	}
	public void setCode(ErrorCode code) {
		this.code = code;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
