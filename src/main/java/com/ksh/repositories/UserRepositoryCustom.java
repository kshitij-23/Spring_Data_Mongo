package com.ksh.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.ksh.documents.MonthWiseBirthDateCount;

@Repository
public class UserRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;
	
//	public List<MonthWiseBirthDateCount> getHostingCount() {
//		
//		Aggregation agg = newAggregation(
//			group("hosting").count().as("total"),
//			project("total").and("hosting").previousOperation(),
//			sort(Sort.Direction.DESC, "total")
//				
//		);
//
//		//Convert the aggregation result into a List
//		AggregationResults<HostingCount> groupResults 
//			= mongoTemplate.aggregate(agg, Domain.class, HostingCount.class);
//		List<HostingCount> result = groupResults.getMappedResults();
//		
//		return result;
//		
//	}
	
}
