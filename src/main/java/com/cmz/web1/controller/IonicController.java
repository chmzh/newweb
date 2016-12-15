package com.cmz.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ionic/")
public class IonicController {
	@RequestMapping("index")
	public String index(){
		return "ionic";
	}
	
	@RequestMapping("form")
	public String form(){
		return "ionic_form";
	}
}
