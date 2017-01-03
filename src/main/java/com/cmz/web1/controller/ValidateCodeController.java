package com.cmz.web1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cmz.web1.util.ValidateCodeUtil;

@Controller
public class ValidateCodeController {
	@RequestMapping(value="valiCode" , method=RequestMethod.GET)
	public void validateCode(HttpServletRequest request,HttpServletResponse response){
		new ValidateCodeUtil().validateCode(request,response);
	}
	
	@RequestMapping(value="validatecode")
	public String index(){
		return "validatecode";
	}
}
