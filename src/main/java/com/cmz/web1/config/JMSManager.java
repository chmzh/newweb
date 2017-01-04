package com.cmz.web1.config;

import javax.jms.ConnectionFactory;
import javax.jms.MessageListener;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import com.cmz.web1.jms.queue.ConsumerListener;
import com.cmz.web1.jms.queue.ConsumerService;
import com.cmz.web1.jms.queue.ConsumerServiceImpl;
import com.cmz.web1.jms.queue.ConsumerSessionAwareMessageListener;
import com.cmz.web1.jms.queue.ProducerService;
import com.cmz.web1.jms.queue.ProducerServiceImpl;
import com.cmz.web1.jms.queue.QueueMessageListener;
import com.cmz.web1.jms.topic.TopicMessageListener;
import com.cmz.web1.jms.topic.TopicMessageListener2;
import com.cmz.web1.jms.topic.TopicProvider;

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
	
	//========================生产者-消费者
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
	//========================生产者-消费者
	//========================发布-订阅
	
	/**
	 * 定义消息队列（Queue）
	 */
	@Bean("queueDestination2")
	public ActiveMQQueue queueDestination2(){
		return new ActiveMQQueue("queue2");
	}
	/**
	 * 消息队列监听者（Queue)
	 * @return
	 */
	@Bean
	public MessageListener queueMessageListener(){
		QueueMessageListener messageListener = new QueueMessageListener();
		
		return messageListener;
	}
	/**
	 * 消息监听容器（Queue）
	 * @return
	 */
	@Bean
	public MessageListenerContainer messageListenerContainer(){
		DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
		messageListenerContainer.setConnectionFactory(connectionFactory());
		messageListenerContainer.setDestination(queueDestination2());
		messageListenerContainer.setMessageListener(queueMessageListener());
		return messageListenerContainer;
	}
	/**
	 * 定义消息主题（Topic）
	 * @return
	 */
	@Bean("topicDestination")
	public ActiveMQTopic topicDestination(){
		ActiveMQTopic topicDestination = new ActiveMQTopic("my-topic");
		
		return topicDestination;
	}
	/**
	 * 配置JMS模板（Topic）
	 * @return
	 */
	@Bean
	public JmsTemplate topicJmsTemplate(){
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setDefaultDestination(topicDestination());
		jmsTemplate.setPubSubDomain(true);
		jmsTemplate.setReceiveTimeout(10000);
		return jmsTemplate;
	}
	/**
	 * topic消息发布者
	 * @return
	 */
	@Bean
	public TopicProvider topicProvider(){
		TopicProvider topicProvider = new TopicProvider();
		topicProvider.setTopicJmsTemplate(topicJmsTemplate());
		return topicProvider;
	}
	/**
	 * 消息主题监听者(Topic)
	 * @return
	 */
	public TopicMessageListener topicMessageListener(){
		return new TopicMessageListener();
	}
	
	/**
	 * 消息主题监听者(Topic)
	 * @return
	 */
	public TopicMessageListener2 topicMessageListener2(){
		return new TopicMessageListener2();
	}
	
	/**
	 * 主题监听容器 （Topic)
	 * @return
	 */
	@Bean("topicJmsContainer")
	public DefaultMessageListenerContainer defaultMessageListenerContainer(){
		DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
		defaultMessageListenerContainer.setConnectionFactory(connectionFactory());
		defaultMessageListenerContainer.setDestination(topicDestination());
		defaultMessageListenerContainer.setMessageListener(topicMessageListener());
		return defaultMessageListenerContainer;
	}
	/**
	 * 主题监听容器 （Topic)
	 * @return
	 */
	@Bean("topicJmsContainer2")
	public DefaultMessageListenerContainer defaultMessageListenerContainer2(){
		DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
		defaultMessageListenerContainer.setConnectionFactory(connectionFactory());
		defaultMessageListenerContainer.setDestination(topicDestination());
		defaultMessageListenerContainer.setMessageListener(topicMessageListener2());
		return defaultMessageListenerContainer;
	}
	//========================发布-订阅
	
	//========================sessionAware
	/**
	 * 这个是sessionAwareQueue目的地
	 * @return
	 */
	@Bean("sessionAwareQueue")
	public ActiveMQQueue sessionAwareQueue(){
		return new ActiveMQQueue("sessionAwareQueue");
	}
	
	/**
	 * 可以获取session的MessageListener
	 * @return
	 */
	@Bean
	public ConsumerSessionAwareMessageListener consumerSessionAwareMessageListener(){
		ConsumerSessionAwareMessageListener consumerSessionAwareMessageListener = new ConsumerSessionAwareMessageListener();
		consumerSessionAwareMessageListener.setDestination(queueDestination());
		return consumerSessionAwareMessageListener;
	}
	
	/**
	 * 监听sessionAwareQueue 队列的消息，把回复消息写入 queueDestination指向队列，即queue1
	 * @return
	 */
	@Bean
	public DefaultMessageListenerContainer sessionAwareListenerContainer(){
		DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
		defaultMessageListenerContainer.setConnectionFactory(connectionFactory());
		defaultMessageListenerContainer.setDestination(sessionAwareQueue());
		defaultMessageListenerContainer.setMessageListener(consumerSessionAwareMessageListener());
		return defaultMessageListenerContainer;
	}
	
	//========================sessionAware
	
	
	//========================adapter
	/**
	 * 这个是adapterQueue目的地
	 * @return
	 */
	@Bean("adapterQueue")
	public ActiveMQQueue adapterQueue(){
		return new ActiveMQQueue("adapterQueue");
	}
	/**
	 * 消息监听适配器
	 * @return
	 */
	@Bean
	public MessageListenerAdapter messageListenerAdapter(){
		MessageListenerAdapter adapter = new MessageListenerAdapter();
		adapter.setDelegate(new ConsumerListener());
		adapter.setDefaultListenerMethod("receiveMessage");
		return adapter;
	}
	/**
	 * 消息监听适配器对应的监听容器
	 */
	@Bean
	public DefaultMessageListenerContainer messageListenerAdapterContainer(){
		DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
		defaultMessageListenerContainer.setConnectionFactory(connectionFactory());
		defaultMessageListenerContainer.setDestination(adapterQueue());
		defaultMessageListenerContainer.setMessageListener(messageListenerAdapter());
		return defaultMessageListenerContainer;
	}
	
	//========================adapter
}
