package com.ksh.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksh.documents.MonthWiseBirthDateCount;
import com.ksh.documents.User;
import com.ksh.reqrep.ResponseFactory;
import com.ksh.reqrep.UserResponse;
import com.ksh.reqrep.ValError;
import com.ksh.services.UserService;
import com.ksh.validations.UserValidation;

/**
KSHITIJ
December 24, 2018
**/

@RestController
@RequestMapping("/user/**")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	public UserResponse save(@RequestBody User user) {
		
		ValError[] valErrors = UserValidation.validate(user, "Insert");
		UserResponse userResponse = null;
		if(valErrors != null && valErrors.length > 0) {
			userResponse = ResponseFactory.getUserCreateFailureResp(valErrors);
		}
		else {
			boolean result = checkEmailExists(user.getEmail());
			if(result) {
				userResponse = ResponseFactory.getEmailExistResp();
				return userResponse; 
			}
			user.setId(null); user.setActive(true);
			User user2 = userService.save(user);
			if(user2.getId() != null && !user2.getId().isEmpty()) {
				userResponse = ResponseFactory.getUserCreateSuccessResp(user2.getId());
			}
		}
		return userResponse;
	}
	
	@RequestMapping("/update")
	public UserResponse update(@RequestBody User user) {
		
		ValError[] valErrors = UserValidation.validate(user, "Update");
		UserResponse userResponse = new UserResponse();
		if(valErrors != null && valErrors.length > 0) {
			userResponse = ResponseFactory.getUserUpdateFailureResp(valErrors);
		}
		else {
			Optional<User> optionalUser = userService.findById(user.getId());
			if(optionalUser.isPresent()) {
				User user2 = optionalUser.get();
				user2.setPinCode(user.getPinCode());
				user2.setBirthDate(user.getBirthDate());
				userService.save(user2);
				userResponse = ResponseFactory.getUserUpdateSuccessResp(user2.getId());
			}
			else {
				userResponse = ResponseFactory.getUserNotExistResp(user.getId());
			}
		}
		return userResponse;
	}
	
	@RequestMapping("/delete")
	public UserResponse delete(@RequestBody User user) {
		
		ValError[] valErrors = UserValidation.validate(user, "Delete");
		UserResponse userResponse = null;
		if(valErrors != null && valErrors.length > 0) {
			userResponse = ResponseFactory.getUserDeleteFailureResp(valErrors);
		}
		else {
			Optional<User> optionalUser = userService.findById(user.getId());
			if(optionalUser.isPresent()) {
				User user2 = optionalUser.get(); 
				user2.setActive(false);
				userService.save(user2);
				userResponse = ResponseFactory.getUserDeleteSuccessResp(user.getId());
			}
			else {
				userResponse = ResponseFactory.getUserNotExistResp(user.getId());
			}
		}
		return userResponse;
	}
	
	@RequestMapping("/findAll")
	public List<User> findAll() {
		return userService.findAll();
	}
	
	@RequestMapping("/findById/{id}")
	public User findById(@PathVariable String id) {
		Optional<User> optionalUser = userService.findById(id);
		if(optionalUser.isPresent())
			return optionalUser.get();
		else
			return null;
	}
	
	@RequestMapping("/currentMonthBirthdays")
	public List<User> currentMonthBirthdays() {
		LocalDate initial = LocalDate.now();
		LocalDate start = initial.withDayOfMonth(1);
		start = start.minusDays(1);
		LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
		List<User> users = userService.findByBirthDateBetween(start, end);
		return users;
	}
	
	@RequestMapping("/monthWiseBirthdatesCount")
	public List<MonthWiseBirthDateCount> monthWiseBirthdatesCount() {
		return userService.monthWiseBirthdatesCount();
	}
	
	@RequestMapping("/dummyUser")
	public User dummyUser() {
		User user = new User();
		user.setBirthDate(LocalDate.now());
		return user;
	}
	
	@RequestMapping("/testService")
	public String testService() {
		return "Services Are Running...";
	}
	
	private boolean checkEmailExists(String email) {
		Integer integer = userService.countByEmailAndIsActive(email, true);
		if(integer != null && integer > 0) 
			return true;
		else 
			return false;
	}
	
//	@RequestMapping("/saveAll")
//	public void saveAll(@RequestBody List<User> users) {
//		userService.saveAll(users);
//	}
//	
//	@RequestMapping("/existsById/{id}")
//	public boolean existsById(@PathVariable String id) {
//		return userService.existsById(id);
//	}
//	
//	@RequestMapping("/count")
//	public long count() {
//		return userService.count();
//	}
//	
//	@RequestMapping("/deleteAll")
//	public void deleteAll() {
//		userService.deleteAll();
//	}
//	
//	@RequestMapping("/deleteById/{id}")
//	public void deleteById(@PathVariable String id) {
//		userService.deleteById(id);
//	}
	
}
