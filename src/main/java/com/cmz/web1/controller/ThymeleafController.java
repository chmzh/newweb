package com.cmz.web1.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cmz.web1.domain.PersonForm;
import com.cmz.web1.validator.PersonValidator;

@Controller
@RequestMapping("/th/")
@EnableWebMvc
public class ThymeleafController {

	
//	@InitBinder  
//    public void initBinder(DataBinder binder) {  
//       binder.setValidator(new PersonValidator());  
//    } 
	
	@RequestMapping("form")
	public String showForm(PersonForm personForm) {
		return "form";
	}

	@RequestMapping(value="form1",method=RequestMethod.POST)
	public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "form";
		}

		return "redirect:/results";
	}
}
