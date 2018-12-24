package com.ksh.reqrep;

import java.io.Serializable;

/**
KSHITIJ
Dec 24, 2018
**/
public class UserResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String resMsg;
	private String userId;
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

