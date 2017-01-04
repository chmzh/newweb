package com.cmz.web1.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import com.cmz.web1.jms.queue.ConsumerService;
import com.cmz.web1.jms.queue.ConsumerServiceImpl;
import com.cmz.web1.jms.queue.ProducerService;
import com.cmz.web1.jms.queue.ProducerServiceImpl;

@Configuration
public class JMSManager {
	/**
	 * 配置JMS连接工厂
	 * @return
	 */
	@Bean
	public ConnectionFactory connectionFactory(){
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL("failover:(tcp://localhost:61616)");
		return connectionFactory;
	}
	/**
	 * 定义消息队列（Queue）
	 */
	@Bean("queueDestination1")
	public ActiveMQQueue queueDestination(){
		return new ActiveMQQueue("queue1");
	}
	/**
	 * 配置JMS模板（Queue），Spring提供的JMS工具类，它发送、接收消息。
	 */
	@Bean
	public JmsTemplate jmsTemplate(){
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setDefaultDestination(queueDestination());
		jmsTemplate.setReceiveTimeout(1000);
		return jmsTemplate;
	}
	/**
	 * queue1 消息生产者
	 * @return
	 */
	@Bean
	public ProducerService producerService(){
		ProducerServiceImpl producerService = new ProducerServiceImpl();
		producerService.setJmsTemplate(jmsTemplate());
		return producerService;
	}
	/**
	 * queue1 消息消费者
	 * @return
	 */
	@Bean
	public ConsumerService consumerService(){
		ConsumerServiceImpl consumerService = new ConsumerServiceImpl();
		consumerService.setJmsTemplate(jmsTemplate());
		return consumerService;
	}
	
	
	/**
	 * 定义消息队列（Queue）
	 */
	@Bean("queueDestination2")
	public ActiveMQQueue queueDestination2(){
		return new ActiveMQQueue("queue2");
	}
}
