package com.cmz.web1.config;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.Mongo;


@Configuration
public class MongoDBManager {
	@Bean
	Mongo mongo() throws UnknownHostException{
		Mongo mongo = new Mongo();
	
		return mongo;
	}
	
	MongoDbFactory mongoDbFactory(){
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(uri);
		return mongoDbFactory
	}
	
	MongoTemplate mongoTemplate(){
		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		return template;
	}
	
}
