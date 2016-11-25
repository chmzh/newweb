package com.cmz.web1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cmz.web1.data.util.RedisClient;
import com.cmz.web1.domain.User;


@Controller
@RequestMapping("redis/")
public class RedisController {
	
	@Autowired
	private RedisClient<String, String> client;
	@Autowired
	private RedisClient<String, User> uclient;
	@RequestMapping("index")
	public void index(){
		String v = client.getString("aaa");
		client.setString("aaa", "user1");
		v = client.getString("aaa");
		System.out.println(v);
		
		
		User u = new User();
		u.setId(1);
		u.setName("你好");
		User u1 = uclient.getObject("user1", u.getId());
		uclient.setObject("user1", u.getId(), u);
		
		u1 = uclient.getObject("user1", u.getId());
		
		System.out.println(u1.getId()+":"+u1.getName());
		
	}
}
