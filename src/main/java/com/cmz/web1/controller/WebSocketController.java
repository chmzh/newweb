package com.cmz.web1.controller;

import org.springframework.data.mongodb.core.index.Index;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cmz.web1.entries.Greeting;
import com.cmz.web1.entries.HelloMessage;

@Controller
public class WebSocketController {


	@RequestMapping("websocket")
	public String index(){
		return "websocket";
	}
	
    @MessageMapping("hello")
    @SendTo("topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        //Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

}
