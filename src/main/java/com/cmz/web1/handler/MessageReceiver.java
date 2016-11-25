package com.cmz.web1.handler;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MessageReceiver implements MessageListener {

	public void onMessage(Message message) {
		System.out.println(new String(message.getBody()));
	}

}
