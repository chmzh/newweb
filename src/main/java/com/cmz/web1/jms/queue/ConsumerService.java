package com.cmz.web1.jms.queue;

import javax.jms.Destination;

public interface ConsumerService {
  public String receive(Destination queueDestination);
}
