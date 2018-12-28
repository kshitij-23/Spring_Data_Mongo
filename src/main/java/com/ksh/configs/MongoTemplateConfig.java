package com.ksh.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
public class MongoTemplateConfig {

	@Bean
	public MongoClient mongoClient() {
		return new MongoClient("localhost");
	}

	@Bean 
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), "spring_data_mongo");
	}

}
