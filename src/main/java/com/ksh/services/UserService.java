package com.ksh.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ksh.documents.MonthWiseBirthDateCount;
import com.ksh.documents.User;

/**
KSHITIJ
December 24, 2018
**/
public interface UserService {

	public List<User> findAll();	
	public Optional<User> findById(String id);
	public User save(User user);
	public void delete(User user);
	public Integer countByEmailAndIsActive(String email, boolean isActive);
	public List<User> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);
	public List<MonthWiseBirthDateCount> monthWiseBirthdatesCount(); 
	
//	public void saveAll(List<User> users);
//	public boolean existsById(String id);
//	public long count();
//	public void deleteAll();
//	public void deleteById(String id);
	
}
