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
@PropertySource("/WEB-INF/conf/impala.properties")
// @Order(1)
public class ImpalaManager {

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
}
