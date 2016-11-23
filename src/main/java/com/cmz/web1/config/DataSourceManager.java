package com.cmz.web1.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan("com.cmz.web1.dao")
@PropertySource("/WEB-INF/conf/jdbc.properties")
@EnableTransactionManagement
//@Order(1)
public class DataSourceManager {

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.driver}")
	private String driver;

	@Bean
	public DataSource dataSource() {
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
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
    		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    		sqlSessionFactoryBean.setDataSource(dataSource);
    		return sqlSessionFactoryBean;
    }
}
