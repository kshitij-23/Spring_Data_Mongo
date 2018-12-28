package com.ksh.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ksh.documents.User;

/**
KSHITIJ
December 24, 2018
**/

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public Integer countByEmailAndIsActive(String email, boolean isActive);
	public List<User> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);
//	Below Query Can be used for Above requirement.
//	db.users.find({
//		   "$expr": { 
//		       "$and": [
//		            { "$eq": [ { "$month" : "$birthDate" }, { "$month" : new Date() } ] }
//		       ]
//		    }
//		});

//	@Query("db.getCollection('users').aggregate\r\n" + 
//			"([{$group:{_id:{month: { $month: \"$birthDate\" }}, count: { $sum:1 },date: { $first: \"$birthDate\" }}},\r\n" + 
//			"  {$project:{month:{$dateToString: { format: \"%m\", date: \"$date\" }},count: 1,_id: 0}}\r\n" + 
//			"])")
	public Map<String, String> countBybirthDate(); 
	
}
