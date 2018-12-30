package com.ksh.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import com.ksh.documents.MonthWiseBirthDateCount;

/**
KSHITIJ
Dec 30, 2018
**/

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<MonthWiseBirthDateCount> monthWiseBirthdatesCount() {
		Aggregation agg = Aggregation.newAggregation(
				Aggregation.group("month1").first("birthDate").as("date").count().as("count"),
				Aggregation.project("_id").and("date").dateAsFormattedString("%m").as("month").and("count").as("count").andExpression("month(date)").as("month1")
			);
		
		System.out.println("Query  ==>>["+agg.toString()+"]");
		AggregationResults<MonthWiseBirthDateCount> data = mongoTemplate.aggregate(agg, "users", MonthWiseBirthDateCount.class);
		System.out.println(data.toString());
		List<MonthWiseBirthDateCount> list = new ArrayList<>();
		data.forEach((d) -> {
			System.out.println("Count : "+d.getCount()+" "+"Month : "+d.getMonth());
			list.add(d);
			});
		return list;
	}

//	Query is giving perfect output but I was failed to convert into Spring Data Aggregate Query
//	db.getCollection('users').aggregate( 
//			[{ 
//			        $group: 
//			        { 
//			            _id: 
//			            { 
//			                month: { $month: "$birthDate" }, 
//			            }, 
//			            count: { $sum:1 }, 
//			            date: { $first: "$birthDate" } 
//			        } 
//			    }, 
//			    { 
//			        $project: 
//			        { 
//			            month: 
//			            { 
//			                $dateToString: { format: "%m", date: "$date" } 
//			            }, 
//			            count: 1, 
//			            _id: 0 
//			        } 
//			    }])
	

}
