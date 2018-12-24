package com.ksh.services;

import java.util.List;

import com.ksh.documents.User;

/**
KSHITIJ
December 24, 2018
**/
public interface UserService {

	public List<User> findAll();	
	public User findById(String id);
	public User save(User user);
	public void saveAll(List<User> users);
	public boolean existsById(String id);
	public long count();
	public void deleteAll();
	public void deleteById(String id);
	public void delete(User user);
}
