package com.cmz.web1.controller;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmz.web1.jms.queue.ConsumerService;
import com.cmz.web1.jms.queue.ProducerService;
import com.cmz.web1.jms.topic.TopicProvider;

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
	@Autowired
	@Qualifier("sessionAwareQueue")
	private ActiveMQQueue sessionAwareQueue;

	@Autowired
	@Qualifier("adapterQueue")
	private ActiveMQQueue adapterQueue;

	@Autowired
	private TopicProvider topicProvider;
	@Autowired
	// @Qualifier("topicDestination")
	private ActiveMQTopic topic;

	/**
	 * 测试生产者向queue1发送消息
	 */
	@RequestMapping("send")
	@ResponseBody
	public String send(String msg) {
		producerService.sendMessage(msg);
		return msg;
	}

	/**
	 * 测试消费者从queue1接受消息
	 */
	@RequestMapping("rec")
	@ResponseBody
	public String rec(String msg) {
		return consumerService.receive(queueDestination);
	}

	/**
	 * 测试主题监听
	 * 
	 * 1.生产者向主题发布消息
	 * 
	 * 2.ConsumerMessageListener监听主题，并消费消息
	 */
	@RequestMapping("publish")
	@ResponseBody
	public String publish(String msg) {
		topicProvider.publish(topic, msg);
		return msg;
	}

	/**
	 * 测试SessionAwareMessageListener
	 * 
	 * 1. 生产者向队列sessionAwareQueue发送消息
	 * 
	 * 2. SessionAwareMessageListener接受消息，并向queue1队列发送回复消息
	 * 
	 * 3. 消费者从queue1消费消息
	 * 
	 */
	@RequestMapping("aware")
	@ResponseBody
	public String testAware() {
		producerService.sendMessage(sessionAwareQueue, "Hello sessionAware");
		consumerService.receive(queueDestination);
		return null;
	}

	/**
	 * 测试MessageListenerAdapter
	 * 
	 * 1. 生产者向队列adapterQueue发送消息
	 * 
	 * 2. MessageListenerAdapter使ConsumerListener接受消息，并向queue1队列发送回复消息
	 * 
	 * 3. 消费者从queue1消费消息
	 * 
	 */
	@RequestMapping("adapter")
	@ResponseBody
	public String testAdapter() {
		producerService.sendMessage(adapterQueue, "Hello adapterQueue", queueDestination);
		consumerService.receive(queueDestination);
		return null;
	}
}
