package com.ksh.validations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.ksh.documents.User;
import com.ksh.reqrep.ErrorCode;
import com.ksh.reqrep.ValError;

/**
KSHITIJ
Dec 24, 2018
**/
public class UserValidation {

	public static ValError[] validate(User user, String operation) 
	{
		List<ValError> valErrors = new ArrayList<>();
		if("Update".equalsIgnoreCase(operation) || "Delete".equalsIgnoreCase(operation))
		{
			validate(user, (u) -> user.getId() == null || user.getId().isEmpty(), "ID").ifPresent((v) -> valErrors.add(v)); 
		}
		else if("Update".equalsIgnoreCase(operation) || "Insert".equalsIgnoreCase(operation))
		{
			validate(user, (u) -> user.getfName() == null || user.getfName().isEmpty(), "fNname").ifPresent((v) -> valErrors.add(v));
			validate(user, (u) -> user.getlName() == null || user.getlName().isEmpty(), "lName").ifPresent((v) -> valErrors.add(v));
			validate(user, (u) -> user.getEmail() == null || user.getEmail().isEmpty(), "email").ifPresent((v) -> valErrors.add(v));
			validate(user, (u) -> user.getPinCode() > 0, "pinCode").ifPresent((v) -> valErrors.add(v));
			validate(user, (u) -> user.getBirthDate() > 0, "pinCode").ifPresent((v) -> valErrors.add(v));
		}
		return (ValError[]) valErrors.toArray();
	}
	
	private static Optional<ValError> validate(User user, Predicate<User> predicate, String field) 
	{
		if(predicate.test(user)) {
			ValError valError = new ValError(ErrorCode.VALIDATION_ERROR, field, field+ " is required.");
			return Optional.of(valError);
		}
		else {
			 return Optional.empty(); 
		}
	}

}
