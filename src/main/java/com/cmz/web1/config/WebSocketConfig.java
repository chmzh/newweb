package com.cmz.web1.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.cmz.web1.handler.HandShake;
import com.cmz.web1.handler.MyWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig{
	
}
//public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
//
//	@Bean
//	public MyWebSocketHandler handler(){
//		return new MyWebSocketHandler();
//	}
//
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		registry.addHandler(handler(), "/ws").addInterceptors(new HandShake());
//
//		registry.addHandler(handler(), "/ws/sockjs").addInterceptors(new HandShake()).withSockJS();
//	}
//
//}