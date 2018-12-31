package com.ksh.reqrep;

public class ResponseFactory {

	public static UserResponse getUserResponse(String id, ValError[] valErrors, String msg) {
		UserResponse userResponse = new UserResponse();
		userResponse.setResMsg(msg);
		if(id != null) userResponse.setUserId(id);
		if(valErrors != null) userResponse.setValErrors(valErrors);
		return userResponse;
	}
	
//	public static UserResponse userFailureResp(ValError[] valErrors, String msg) {
//		UserResponse userResponse = new UserResponse();
//		userResponse.setResMsg(msg);
//		userResponse.setValErrors(valErrors);
//		return userResponse;
//	}
//	public static UserResponse getEmailExistResp() {
//		UserResponse userResponse = new UserResponse();
//		userResponse.setResMsg("");
//		return userResponse;
//	}
//	public static UserResponse getUserNotExistResp(String id) {
//		UserResponse userResponse = new UserResponse();
//		userResponse.setResMsg("User does not exists.");
//		userResponse.setUserId(id);
//		return userResponse;
//	}
}
