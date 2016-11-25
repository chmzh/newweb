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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cmz.web1.data.util.ImpalaClient;

@Configuration
@MapperScan("com.cmz.web1.dao")
@PropertySource({"/WEB-INF/conf/jdbc.properties","/WEB-INF/conf/impala.properties"})
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
    public SqlSessionFactoryBean sqlSessionFactoryBean(){
    		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    		sqlSessionFactoryBean.setDataSource(mysqlDataSource());
    		return sqlSessionFactoryBean;
    }
    @Value("${impalaURL}")
    private String impalaURL;
    @Value("${impalaDriverClass}")
    private String impalaDriverClass;
    	@Value("${impalaInitialSize}")
    	private int impalaInitialSize;
    	@Value("${impalaMaxtotal}")
    	private int impalaMaxtotal;
    	@Value("${impalaMaxIdle}")
    	private int impalaMaxIdle;
    	@Value("${impalaMaxWaitMillis}")
    	private int impalaMaxWaitMillis;
    	@Value("${impalaMinIdle}")
    	private int impalaMinIdle;
    	@Value("${impalaHeart}")
    	private String impalaHeart;
    
    @Bean
    public DataSource impalaDataSource(){
    		BasicDataSource dataSource = new BasicDataSource(); 
    		dataSource.setDriverClassName(impalaDriverClass);  
    		dataSource.setUsername("");  
    		dataSource.setPassword("");  
    		dataSource.setUrl(impalaURL);  
    		dataSource.setInitialSize(impalaInitialSize); // 初始的连接数；  
            //ds.setMaxActive(maxtotal); 
    		dataSource.setMaxTotal(impalaMaxtotal);
    		dataSource.setMaxIdle(impalaMaxIdle);  
            //ds.setMaxWait(maxWaitMillis); 
    		dataSource.setMaxWaitMillis(impalaMaxWaitMillis);
    		dataSource.setMinIdle(impalaMinIdle);  
    		dataSource.setValidationQuery(impalaHeart);
    		return dataSource;
    }
    @Bean
    public ImpalaClient impalaClient(){
    		ImpalaClient client = new ImpalaClient();
    		client.setDataSource(impalaDataSource());
    		return client;
    }
}
