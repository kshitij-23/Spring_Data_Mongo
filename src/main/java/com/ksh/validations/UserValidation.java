package com.ksh.validations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.ksh.documents.User;
import com.ksh.reqrep.ErrorCode;
import com.ksh.reqrep.ValError;

/**
 * KSHITIJ December 24, 2018
 **/

public class UserValidation {

	public static ValError[] validate(User user, String operation) {
		List<ValError> valErrors = new ArrayList<>();
		if ("Delete".equalsIgnoreCase(operation)) 
		{
			validate(user, (u) -> (user.getId() == null || user.getId().isEmpty()), "ID")
					.ifPresent((v) -> valErrors.add(v));
		} 
		else if ("Update".equalsIgnoreCase(operation)) 
		{
			validate(user, (u) -> (user.getId() == null || user.getId().isEmpty()), "ID")
					.ifPresent((v) -> valErrors.add(v));
			validate(user, (u) -> (user.getPinCode() == 0 || String.valueOf(user.getPinCode()).length() < 6), "pinCode")
					.ifPresent((v) -> valErrors.add(v));
			validate(user, (u) -> (user.getBirthDate() == null || user.getBirthDate().isAfter(LocalDate.now())),
					"birthDate").ifPresent((v) -> valErrors.add(v));
		} 
		else if ("Insert".equalsIgnoreCase(operation)) 
		{
			validate(user, (u) -> (user.getfName() == null || user.getfName().isEmpty()), "fNname")
					.ifPresent((v) -> valErrors.add(v));
			validate(user, (u) -> (user.getlName() == null || user.getlName().isEmpty()), "lName")
					.ifPresent((v) -> valErrors.add(v));
			validate(user, (u) -> (user.getEmail() == null || user.getEmail().isEmpty()), "email")
					.ifPresent((v) -> valErrors.add(v));
			validate(user, (u) -> (user.getPinCode() == 0 || String.valueOf(user.getPinCode()).length() < 6), "pinCode")
					.ifPresent((v) -> valErrors.add(v));
			validate(user, (u) -> (user.getBirthDate() == null || user.getBirthDate().isAfter(LocalDate.now())),
					"birthDate").ifPresent((v) -> valErrors.add(v));
		}
		ValError[] valErrors2 = new ValError[valErrors.size()];
		if (valErrors2.length > 0) {
			for (int i = 0; i < valErrors2.length; i++) {
				valErrors2[i] = valErrors.get(i);
			}
		}
		return valErrors2;
	}

	private static Optional<ValError> validate(User user, Predicate<User> predicate, String field) {
		if (predicate.test(user)) {
			ValError valError = null;
			if (field.equalsIgnoreCase("birthdate") || field.equalsIgnoreCase("pinCode"))
				valError = new ValError(ErrorCode.VALIDATION_ERROR, field, field + " is not valid.");
			else
				valError = new ValError(ErrorCode.VALIDATION_ERROR, field, field + " is required.");
			return Optional.of(valError);
		} else {
			return Optional.empty();
		}
	}

}
