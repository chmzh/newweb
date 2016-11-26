package com.cmz.web1.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.cmz.web1.data.util.RabbitClient;
import com.cmz.web1.handler.MessageReceiver;

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
	
	public final static String queueName = "myqueue";
	
    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }
	
	@Bean
	TopicExchange exchange() {
        return new TopicExchange("myexchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }
	
	@Bean
	CachingConnectionFactory cachingConnectionFactory(){
		CachingConnectionFactory factory = new CachingConnectionFactory();
		factory.setHost(rmqHost);
		factory.setPort(rmqPort);
		factory.setUsername(rmqManagerUser);
		factory.setPassword(rmqManagerPassword);
		return factory;
	}
	
	@Bean
    SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory,
    		MessageListener messageListener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(messageListener);
        return container;
    }
	@Bean
	MessageListener messageListener(){
		MessageReceiver receiver = new MessageReceiver();
		
		return receiver;
	}
	
	@Bean
	RabbitAdmin rabbitAdmin(CachingConnectionFactory cachingConnectionFactory){
		RabbitAdmin rabbitAdmin = new RabbitAdmin(cachingConnectionFactory);
		return rabbitAdmin;
	}
	

	
	@Bean
	RabbitClient rabbitClient(CachingConnectionFactory cachingConnectionFactory){
		RabbitClient rabbitClient = new RabbitClient(cachingConnectionFactory);
		return rabbitClient;
	}
}
