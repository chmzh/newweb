package com.cmz.web1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmz.web1.service.GroovyService;

@Controller
@RequestMapping("/groovy/")
public class GroovyController {
	
	@Autowired
	private GroovyService groovyService;
	
	@RequestMapping("index")
	@ResponseBody
	public String index(){
		return groovyService.getMessage();
	}
}
