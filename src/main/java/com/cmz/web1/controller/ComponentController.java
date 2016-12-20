package com.cmz.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/component/")
public class ComponentController {
	@RequestMapping("table")
	public String table(){
		return "component/table";
	}
}
