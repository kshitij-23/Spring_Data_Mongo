package com.ksh.repositories;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.ksh.documents.MonthWiseBirthDateCount;


@Repository
public interface UserRepositoryCustom {

	public List<MonthWiseBirthDateCount> monthWiseBirthdatesCount();

}
