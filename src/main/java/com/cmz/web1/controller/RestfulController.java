package com.cmz.web1.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmz.web1.domain.User;

@RestController
public class RestfulController {
	@RequestMapping(method = RequestMethod.GET,value="/test/{id}")
	public User test(@PathVariable(value="id")int id){
		
		User user = new User();
		user.setId(id);
		user.setName("你好");
		
		return user;
}
}
