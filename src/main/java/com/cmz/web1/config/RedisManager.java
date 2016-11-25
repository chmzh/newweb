package com.cmz.web1.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
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
import com.cmz.web1.data.util.RedisClient;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("/WEB-INF/conf/redis.properties")
// @Order(1)
public class RedisManager {

	@Value("${redis.host}")
	private String redisHost;
	@Value("${redis.port}")
	private int redisPort;
	@Value("${redis.pass}")
	private String redisPass;
	@Value("${redis.maxIdle}")
	private int redisMaxIdle;
	@Value("${redis.maxActive}")
	private int redisMaxActive;
	@Value("${redis.maxTotal}")
	private int redisMaxTotal;
	@Value("${redis.maxWaitMillis}")
	private long redisMaxWaitMillis;
	@Value("${redis.testOnBorrow}")
	private boolean redisTestOnBorrow;
	
	@Bean
	public JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(redisMaxIdle);
		config.setMaxTotal(redisMaxTotal);
		config.setMaxWaitMillis(redisMaxWaitMillis);
		config.setTestOnBorrow(redisTestOnBorrow);
		return config;
	}
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(){
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setPoolConfig(jedisPoolConfig());
		factory.setHostName(redisHost);
		factory.setPort(redisPort);
		factory.setPassword(redisPass);
		
		return factory;
	}
	
	@Bean
	public StringRedisSerializer stringRedisSerializer(){
		StringRedisSerializer serializer = new StringRedisSerializer();
		return serializer;
	}
	
	@Bean
	public <K,V> RedisClient<K,V> redisClient() {
		RedisClient<K,V> redisClient = new RedisClient<K,V>();
		redisClient.setEnableTransactionSupport(true);
		redisClient.setJedisConnectionFactory(jedisConnectionFactory());
		redisClient.setKeySerializer(stringRedisSerializer());
		redisClient.init();
		return redisClient;
	}
}
