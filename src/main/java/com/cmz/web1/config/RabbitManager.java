package com.cmz.web1.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cmz.web1.data.util.ImpalaClient;
import com.cmz.web1.data.util.RabbitClient;
import com.cmz.web1.data.util.RedisClient;

import redis.clients.jedis.JedisPoolConfig;

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
	
	@Bean
	public CachingConnectionFactory cachingConnectionFactory(){
		CachingConnectionFactory factory = new CachingConnectionFactory();
		factory.setHost(rmqHost);
		factory.setPort(rmqPort);
		factory.setUsername(rmqManagerUser);
		factory.setPassword(rmqManagerPassword);
		
		return factory;
	}
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
