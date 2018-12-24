package com.ksh.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ksh.documents.User;

/**
KSHITIJ
December 24, 2018
**/

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
