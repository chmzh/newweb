package com.cmz.web1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmz.web1.service.MUserService;
import com.mongodb.DBCollection;

@Controller
@RequestMapping("/mongo/")
public class MongoController {
	
	@Autowired
	private MUserService mUserService;
	
	@RequestMapping("index")
	@ResponseBody
	public String test(){
		String name = mUserService.getUser();
		return name;
	}
}
