package com.cmz.web1.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;

import com.google.common.cache.CacheBuilder;

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
	
	@Bean  
    @Primary  
    public SimpleCacheManager guavaCacheManager() {  
        SimpleCacheManager cacheManager = new SimpleCacheManager();  
  
        //把各个cache注册到cacheManager中，GuavaCache实现了org.springframework.cache.Cache接口  
        ArrayList<GuavaCache> caches = new ArrayList<GuavaCache>();  

        caches.add(new GuavaCache("guava", CacheBuilder.newBuilder().recordStats().expireAfterWrite(60, TimeUnit.SECONDS).maximumSize(10000).build()));  

        cacheManager.setCaches(caches);  
        return cacheManager;  
    }  
	
	/*
	@Autowired  
    private JedisCluster jedisCluster;  
  
    //创建基于redis的Cache Manager   
    @Bean  
    public CacheManager redisCacheManager() {  
        JedisClusterCacheManager cacheManager = new JedisClusterCacheManager(jedisCluster);  
  
        ArrayList<JedisClusterCache> caches = new ArrayList<JedisClusterCache>();  
  
        //把各个cache注册到cacheManager中，JedisClusterCache实现了org.springframework.cache.Cache接口  
        for(Caches c: Caches.values()){  
            caches.add(new JedisClusterCache(c.name(), jedisCluster, c.getTtl()));  
        }  
        cacheManager.setCaches(caches);  
        return cacheManager;  
    }  
    */
}
