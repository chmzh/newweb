package com.cmz.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 头部固定动画
 * @author chenmingzhou
 *
 */
@Controller
@RequestMapping("/head/")
public class HeadRoomController {
	@RequestMapping("index")
	public String index(){
		return "headroom";
	}
	
	@RequestMapping("index1")
	public String index1(){
		return "headroom1";
	}
}
