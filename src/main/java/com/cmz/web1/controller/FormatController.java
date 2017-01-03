package com.cmz.web1.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmz.web1.domain.PersonForm;

@Controller
@RequestMapping("/format/")
public class FormatController {
	@RequestMapping("index")
	public String index(Model model){
		PersonForm personForm = new PersonForm();
		personForm.setAge(1);
		//personForm.setDate(new Date());
		personForm.setName("name");
		model.addAttribute("person",personForm);
		return "format";
	}
	
	@RequestMapping(method = RequestMethod.GET,value="json")
	@ResponseBody
	public PersonForm test(){
		
		PersonForm user = new PersonForm();
		user.setAge(1);;
		user.setName("你好");
		//user.setDate(new Date());
		return user;
}
}
