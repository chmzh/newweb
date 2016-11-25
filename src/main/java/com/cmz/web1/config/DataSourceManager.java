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
@MapperScan("com.cmz.web1.dao")
@PropertySource({ "/WEB-INF/conf/jdbc.properties","/WEB-INF/conf/impala.properties","/WEB-INF/conf/redis.properties"})
@EnableTransactionManagement
// @Order(1)
public class DataSourceManager {

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.driver}")
	private String driver;

	@Bean("mysql")
	public DataSource mysqlDataSource() {
		try {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setPassword(password);
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setDriverClassName(Class.forName(driver).getName());
			return dataSource;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(mysqlDataSource());
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean() {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(mysqlDataSource());
		return sqlSessionFactoryBean;
	}

	@Value("${impala.url}")
	private String impalaURL;
	@Value("${impala.driverClass}")
	private String impalaDriverClass;
	@Value("${impala.initialSize}")
	private int impalaInitialSize;
	@Value("${impala.maxtotal}")
	private int impalaMaxtotal;
	@Value("${impala.maxIdle}")
	private int impalaMaxIdle;
	@Value("${impala.maxWaitMillis}")
	private int impalaMaxWaitMillis;
	@Value("${impala.minIdle}")
	private int impalaMinIdle;
	@Value("${impala.heart}")
	private String impalaHeart;

	@Bean
	public DataSource impalaDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(impalaDriverClass);
		dataSource.setUsername("");
		dataSource.setPassword("");
		dataSource.setUrl(impalaURL);
		dataSource.setInitialSize(impalaInitialSize); // 初始的连接数；
		// ds.setMaxActive(maxtotal);
		dataSource.setMaxTotal(impalaMaxtotal);
		dataSource.setMaxIdle(impalaMaxIdle);
		// ds.setMaxWait(maxWaitMillis);
		dataSource.setMaxWaitMillis(impalaMaxWaitMillis);
		dataSource.setMinIdle(impalaMinIdle);
		dataSource.setValidationQuery(impalaHeart);
		return dataSource;
	}

	@Bean
	public ImpalaClient impalaClient() {
		ImpalaClient client = new ImpalaClient();
		client.setDataSource(impalaDataSource());
		return client;
	}

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
