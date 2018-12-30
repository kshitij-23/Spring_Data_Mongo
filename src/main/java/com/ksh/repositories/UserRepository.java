package com.ksh.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ksh.documents.User;

/**
KSHITIJ
December 24, 2018
**/

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {

	public Integer countByEmailAndIsActive(String email, boolean isActive);
	public List<User> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);
	
}
