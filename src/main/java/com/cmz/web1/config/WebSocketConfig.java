package com.cmz.web1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.cmz.web1.handler.SystemWebSocketHandler;

@Configuration  
@EnableWebMvc  
@EnableWebSocket  
public class WebSocketConfig{// extends WebMvcConfigurerAdapter implements WebSocketConfigurer{  
  /*
    @Override  
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {  
        registry.addHandler(systemWebSocketHandler(),"/webSocketServer");  
        registry.addHandler(systemWebSocketHandler(),"/webSocketServer/sockjs").setAllowedOrigins("*").withSockJS();  
         //registry.addHandler(systemWebSocketHandler(),"/webSocketServer").addInterceptors(new WebSocketHandshakeInterceptor());  
         //registry.addHandler(systemWebSocketHandler(), "/sockjs/webSocketServer").addInterceptors(new WebSocketHandshakeInterceptor()).withSockJS();  
         //registry.addHandler(systemWebSocketHandler(), "/webSocketServer/sockjs").withSockJS();  
         //registry.addHandler(systemWebSocketHandler(),"/ws").addInterceptors(new WebSocketHandshakeInterceptor()); 
         //   registry.addHandler(systemWebSocketHandler(), "/ws/sockjs").addInterceptors(new WebSocketHandshakeInterceptor()) 
         //           .withSockJS();
    }  
      
    @Bean  
    public WebSocketHandler systemWebSocketHandler(){  
        return new SystemWebSocketHandler();  
    }  
    */
}