package com.ksh.reqrep;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
KSHITIJ
Dec 24, 2018
**/
public class UserResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonInclude(Include.NON_NULL)
	private String resMsg;
	@JsonInclude(Include.NON_NULL)
	private String userId;
	@JsonInclude(Include.NON_NULL)
	private ValError[] valErrors;
	
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ValError[] getValErrors() {
		return valErrors;
	}
	public void setValErrors(ValError[] valErrors) {
		this.valErrors = valErrors;
	}
	
}

