package com.cmz.web1.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmz.web1.service.UserService;

@Controller
@RequestMapping("user/")
public class UserController {
	 
	@PostConstruct
	public void init(){
		System.out.println(UserController.class.getName()+"init");
	}
	@Autowired
	private UserService userService;
	
	@RequestMapping("hello1")
	@ResponseBody
	public String showBody(){
		return "你好";
	}
	
	@RequestMapping("hello")
	//@ResponseBody
	public String show(Model model){
		//userService.update();
		model.addAttribute("content", "模型中文测试");
		return "hello";
	}
	
	@RequestMapping(value = "/test")  
    public String test(Device device) {  
        if (device.isMobile()) {  
            System.out.println("Hello mobile user!");  
        } else if (device.isTablet()) {  
            System.out.println("Hello tablet user!");  
        } else {  
            System.out.println("Hello desktop user!");  
        }  
        return "test";  
    }  
}
