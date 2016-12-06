package com.cmz.web1.mongodb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.DBCollection;

@Repository
public class MUserDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public String getUser(){
		DBCollection collection = mongoTemplate.createCollection("user");
		return collection.getName();
	}
}
