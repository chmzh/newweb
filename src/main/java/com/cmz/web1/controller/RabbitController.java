package com.cmz.web1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cmz.web1.data.util.RabbitClient;

@Controller
@RequestMapping("rabbit/")
public class RabbitController {
	
	@Autowired
	private RabbitClient rabbitClient;
	
	@RequestMapping("index")
	public void index(){
		
		rabbitClient.sendMessage("a","b","message1");
		rabbitClient.sendMessage("a2", "message2");
	}
}
