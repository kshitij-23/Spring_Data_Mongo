package com.ksh.reqrep;

public class ResponseFactory {

	public static UserResponse getUserCreateSuccessResp(String id) {
		UserResponse userResponse = new UserResponse();
		userResponse.setResMsg("User created Successfully.");
		userResponse.setUserId(id);
		return userResponse;
	}
	public static UserResponse getUserUpdateSuccessResp(String id) {
		UserResponse userResponse = new UserResponse();
		userResponse.setResMsg("User's PinCode and Birthdate are updated Successfully.");
		userResponse.setUserId(id);
		return userResponse;
	}
	public static UserResponse getUserDeleteSuccessResp(String id) {
		UserResponse userResponse = new UserResponse();
		userResponse.setResMsg("User deleted Successfully.");
		userResponse.setUserId(id);
		return userResponse;
	}
	public static UserResponse getUserCreateFailureResp(ValError[] valErrors) {
		UserResponse userResponse = new UserResponse();
		userResponse.setResMsg("Error in user creation.");
		userResponse.setValErrors(valErrors);
		return userResponse;
	}
	public static UserResponse getUserUpdateFailureResp(ValError[] valErrors) {
		UserResponse userResponse = new UserResponse();
		userResponse.setResMsg("Error in user update.");
		userResponse.setValErrors(valErrors);
		return userResponse; 
	}
	public static UserResponse getUserDeleteFailureResp(ValError[] valErrors) {
		UserResponse userResponse = new UserResponse();
		userResponse.setResMsg("Error in user deletion.");
		userResponse.setValErrors(valErrors);
		return userResponse;
	}
	public static UserResponse getEmailExistResp() {
		UserResponse userResponse = new UserResponse();
		userResponse.setResMsg("Email is already exists.");
		return userResponse;
	}
	public static UserResponse getUserNotExistResp(String id) {
		UserResponse userResponse = new UserResponse();
		userResponse.setResMsg("User does not exists.");
		userResponse.setUserId(id);
		return userResponse;
	}
}
