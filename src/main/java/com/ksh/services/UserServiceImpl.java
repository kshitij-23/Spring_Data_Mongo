package com.ksh.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksh.documents.MonthWiseBirthDateCount;
import com.ksh.documents.User;
import com.ksh.repositories.UserRepository;

/**
KSHITIJ
December 24, 2018
**/

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	@Override
	public Optional<User> findById(String id) {
		return userRepository.findById(id);
	}
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}
	@Override
	public Integer countByEmailAndIsActive(String email, boolean isActive) {
		return userRepository.countByEmailAndIsActive(email, isActive);
	}
	@Override
	public List<User> findByBirthDateBetween(LocalDate startDate, LocalDate endDate) {
		return userRepository.findByBirthDateBetween(startDate, endDate);
	}
	@Override
	public List<MonthWiseBirthDateCount> monthWiseBirthdatesCount() {
		return userRepository.monthWiseBirthdatesCount();
	}
	
//	@Override
//	public void saveAll(List<User> users) {
//		userRepository.saveAll(users);
//	}
//	@Override
//	public boolean existsById(String id) {
//		return userRepository.existsById(id);
//	}
//	@Override
//	public long count() {
//		return userRepository.count();
//	}
//	@Override
//	public void deleteAll() {
//		userRepository.deleteAll();
//	}
//	@Override
//	public void deleteById(String id) {
//		userRepository.deleteById(id);
//	}
	

}
