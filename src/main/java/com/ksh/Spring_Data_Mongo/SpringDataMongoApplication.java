package com.ksh.Spring_Data_Mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan("com.ksh")
@EnableMongoRepositories("com.ksh.repositories")
public class SpringDataMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataMongoApplication.class, args);
	}

}

