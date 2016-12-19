package com.cmz.web1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/ckeditor/")
public class CKEditorController {
	@RequestMapping("index")
	public String index(Model model){
		model.addAttribute("content", "");
		return "ckeditor";
	}
	
	@RequestMapping(value="add.do",method=RequestMethod.POST)
	public String add(Model model,String editor1){
		model.addAttribute("content", editor1);
		return "ckeditor";
	}
}
