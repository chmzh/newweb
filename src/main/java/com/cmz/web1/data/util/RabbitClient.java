package com.cmz.web1.data.util;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitClient {
	private RabbitTemplate rabbitTemplate;
	
	public RabbitClient(CachingConnectionFactory cachingConnectionFactory) {
		rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
	}

	public void sendMessage(String exchange, String routingKey, Object object){
		rabbitTemplate.convertAndSend(exchange, routingKey, object);
	}
	

	
	
}
