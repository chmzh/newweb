package com.cmz.web1.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.mobile.device.site.SitePreference;
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
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("hello1")
	@ResponseBody
	public String showBody(){
		List<Map<String, Object>> lists = jdbcTemplate.queryForList("select * from log_test1.zh_consumeinfo where pdate='2016-11-14'");
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
    public String test(HttpServletRequest request) {  
		Device device = DeviceUtils.getCurrentDevice(request);
		
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
