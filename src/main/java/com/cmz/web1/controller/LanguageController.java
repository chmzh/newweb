package com.cmz.web1.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Controller
@RequestMapping("/lang/")
public class LanguageController {
	
	//localhost:8080/newweb/lang/index?lang=zh_CN
	@RequestMapping("index")
	public String index(HttpServletRequest request,@RequestParam(value="lang", defaultValue="zh") String lang){
		Locale locale = new Locale("zh", "CN"); 
		request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,locale);
		return "language";
	}
}
