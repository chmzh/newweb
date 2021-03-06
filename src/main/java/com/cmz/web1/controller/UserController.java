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

import com.cmz.web1.data.util.ImpalaClient;
import com.cmz.web1.domain.User;
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
	
//	@Autowired
//	private ImpalaClient impalaClient;
	
	//@RequestMapping(value="hello1",produces="text/plain;charset=UTF-8")
	@RequestMapping(value="hello1")
	@ResponseBody
	public String showBody(){
		//List<Map<String, Object>> lists = impalaClient.queryForList("select * from log_test1.zh_consumeinfo where pdate='2016-11-14'");
		//System.out.println(lists);
		return "你好";
	}
	
	@RequestMapping("getUser")
	@ResponseBody
	public String getUser(Model model){
		User user = userService.getUser();
		model.addAttribute("content", user.getName());
		return "hello";
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
