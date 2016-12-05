package com.cmz.web1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mongo/")
public class MongoController {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@RequestMapping("index")
	@ResponseBody
	public String test(){
		String dbName = mongoTemplate.getDb().getName();
		return dbName;
	}
}
