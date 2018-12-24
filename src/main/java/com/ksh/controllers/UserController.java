package com.ksh.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksh.documents.User;
import com.ksh.services.UserService;

/**
KSHITIJ
Dec 24, 2018
**/

@RestController
@RequestMapping("/user/**")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/dummyUser")
	public User dummyUser() {
		User user = new User();
		user.setBirthDate(LocalDate.now());
		return user;
	}
	
	@RequestMapping("/findAll")
	public List<User> findAll() {
		return userService.findAll();
	}
	
	@RequestMapping("/findById/{id}")
	public User findById(@PathVariable String id) {
		return userService.findById(id);
	}
	
	@RequestMapping("/save")
	public User save(@RequestBody User user) {
		return userService.save(user);
	}
	
	@RequestMapping("/saveAll")
	public void saveAll(@RequestBody List<User> users) {
		userService.saveAll(users);
	}
	
	@RequestMapping("/existsById/{id}")
	public boolean existsById(@PathVariable String id) {
		return userService.existsById(id);
	}
	
	@RequestMapping("/count")
	public long count() {
		return userService.count();
	}
	
	@RequestMapping("/deleteAll")
	public void deleteAll() {
		userService.deleteAll();
	}
	
	@RequestMapping("/deleteById/{id}")
	public void deleteById(@PathVariable String id) {
		userService.deleteById(id);
	}
	
}
