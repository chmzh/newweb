package com.cmz.web1.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.cmz.web1.data.util.RabbitClient;

@Configuration
@PropertySource("/WEB-INF/conf/rabbit.properties")
// @Order(1)
public class RabbitManager {

	@Value("${rmq.host}")
	private String rmqHost;
	@Value("${rmq.port}")
	private int rmqPort;
	@Value("${rmq.producer.num}")
	private int rmqProducerNum;
	@Value("${rmq.manager.user}")
	private String rmqManagerUser;
	@Value("${rmq.manager.password}")
	private String rmqManagerPassword;
	
	final static String queueName = "spring-boot";
	
	@Bean
	public CachingConnectionFactory cachingConnectionFactory(){
		CachingConnectionFactory factory = new CachingConnectionFactory();
		factory.setHost(rmqHost);
		factory.setPort(rmqPort);
		factory.setUsername(rmqManagerUser);
		factory.setPassword(rmqManagerPassword);
		return factory;
	}
	
//	@Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory,
//    		MessageListener messageListener) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(queueName);
//        container.setMessageListener(messageListener);
//        return container;
//    }
//	@Bean
//	public MessageListener messageListener(){
//		MessageReceiver receiver = new MessageReceiver();
//		
//		return receiver;
//	}
	
	@Bean
	public RabbitAdmin rabbitAdmin(CachingConnectionFactory cachingConnectionFactory){
		RabbitAdmin rabbitAdmin = new RabbitAdmin(cachingConnectionFactory);
		return rabbitAdmin;
	}
	

	
	@Bean
	public RabbitClient rabbitClient(CachingConnectionFactory cachingConnectionFactory){
		RabbitClient rabbitClient = new RabbitClient(cachingConnectionFactory);
		return rabbitClient;
	}
}
