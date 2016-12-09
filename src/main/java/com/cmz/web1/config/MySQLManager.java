package com.cmz.web1.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
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
//@PropertySource("/WEB-INF/conf/jdbc.properties")
@EnableTransactionManagement
// @Order(1)
public class MySQLManager {

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.driver}")
	private String driver;

	@Bean
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
//	@Bean
//	public MapperScannerConfigurer mapperScannerConfigurer(){
//		MapperScannerConfigurer configurer = new MapperScannerConfigurer();
//		configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
//		configurer.setBasePackage("com.cmz.web1.dao");
//		return configurer;
//	}
}
