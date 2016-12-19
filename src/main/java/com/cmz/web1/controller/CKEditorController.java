package com.cmz.web1.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/ckeditor/")
public class CKEditorController {
	@RequestMapping("index")
	public String index(Model model){
		model.addAttribute("content", "aa");
		return "ckeditor";
	}
}
