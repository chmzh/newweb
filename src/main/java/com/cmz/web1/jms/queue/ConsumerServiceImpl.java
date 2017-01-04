package com.cmz.web1.jms.queue;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;

public class ConsumerServiceImpl implements ConsumerService {

  private JmsTemplate jmsTemplate;

  /**
   * 接受消息
   */
  public String receive(Destination destination) {
    TextMessage tm = (TextMessage) jmsTemplate.receive(destination);
    try {
      return "ConsumerService从队列" + destination.toString() + "收到了消息：\t" + tm.getText();
    } catch (JMSException e) {
      e.printStackTrace();
      return e.getMessage();
    }
    
  }

  public void setJmsTemplate(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

}
