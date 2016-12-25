package com.cmz.web1.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cmz.web1.domain.PersonForm;

@Controller
@RequestMapping("/validat/")
public class ValidationController {
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
