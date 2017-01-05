package com.cmz.web1.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("/WEB-INF/dispatcher-servlet.xml")  //加这个表单验证用jsr303 annotation才有效 @Valid 例如ThymeleafController中的例子
@EnableCaching(proxyTargetClass = true)
public class AppConfig {
	@Bean
	public SimpleCacheManager cacheManager(){
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		List<Cache> caches = new ArrayList<>();
		ConcurrentMapCache cache = new ConcurrentMapCache("test");
		caches.add(cache);
		cacheManager.setCaches(caches);
		return cacheManager;
	}
}
