package com.cmz.web1.controller;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmz.web1.jms.queue.ConsumerService;
import com.cmz.web1.jms.queue.ProducerService;

@Controller
@RequestMapping("/jms/")
public class JmsController {
	@Autowired
	private ProducerService producerService;
	@Autowired
	private ConsumerService consumerService;
	@Autowired
	@Qualifier("queueDestination1")
	private ActiveMQQueue queueDestination;
	
	@RequestMapping("send")
	@ResponseBody
	public String send(String msg){
		producerService.sendMessage(msg);
		return msg;
	}
	
	@RequestMapping("rec")
	@ResponseBody
	public String rec(String msg){
		return consumerService.receive(queueDestination);
	}
}
