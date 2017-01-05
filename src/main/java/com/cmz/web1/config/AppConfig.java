package com.cmz.web1.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.aspectj.weaver.tools.cache.SimpleCache;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;

import com.cmz.web1.cache.MemcachedCache;
import com.cmz.web1.cache.MyCacheResolver;
import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.CacheConfiguration;
import com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl;
import com.google.common.cache.CacheBuilder;

@Configuration
@ImportResource("/WEB-INF/dispatcher-servlet.xml")  //加这个表单验证用jsr303 annotation才有效 @Valid 例如ThymeleafController中的例子
//@EnableCaching(proxyTargetClass = true)
@EnableCaching
public class AppConfig implements CachingConfigurer {
	@Bean("cache1")
	public SimpleCacheManager cacheManager(){
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		//CachingConfigurer
		List<Cache> caches = new ArrayList<>();
		ConcurrentMapCache cache = new ConcurrentMapCache("test");
		ConcurrentMapCache cache1 = new ConcurrentMapCache("user");
		caches.add(cache);
		caches.add(cache1);
		cacheManager.setCaches(caches);
		return cacheManager;
	}
	
	@Bean("guava") 
	//@Primary
    public SimpleCacheManager guavaCacheManager() {  
        SimpleCacheManager cacheManager = new SimpleCacheManager();  
  
        //把各个cache注册到cacheManager中，GuavaCache实现了org.springframework.cache.Cache接口  
        ArrayList<GuavaCache> caches = new ArrayList<GuavaCache>();  

        caches.add(new GuavaCache("guava", CacheBuilder.newBuilder().recordStats().expireAfterWrite(60, TimeUnit.SECONDS).maximumSize(10000).build()));  

        cacheManager.setCaches(caches);  
        return cacheManager;  
    }
	@Bean
	@Override
	public CacheResolver cacheResolver() {
		return new MyCacheResolver();
	}
	@Bean
	@Override
	public KeyGenerator keyGenerator() {

		return new SimpleKeyGenerator();
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return new CacheErrorHandler() {
			
			@Override
			public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void handleCacheClearError(RuntimeException exception, Cache cache) {
				// TODO Auto-generated method stub
				
			}
		};
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
	//=====================memcache
	/*
	@Bean
	public SimpleCacheManager memcacheManager(){
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		List<Cache> caches = new ArrayList<>();
		MemcachedCache cache = new MemcachedCache();
		cache.setCache(cacheFactory().getCache());
		caches.add(cache);
		cacheManager.setCaches(caches);
		return cacheManager;
	}
	@Bean
	public CacheFactory cacheFactory(){
		CacheFactory cacheFactory = new CacheFactory();
		cacheFactory.setCacheClientFactory(new MemcacheClientFactoryImpl());
		DefaultAddressProvider addressProvider = new DefaultAddressProvider();
		addressProvider.setAddress("127.0.0.1:11211");
		cacheFactory.setAddressProvider(addressProvider);
		CacheConfiguration configuration = new CacheConfiguration();
		configuration.setConsistentHashing(true);
		cacheFactory.setConfiguration(configuration);
		cacheFactory.setCacheName("default");
		return cacheFactory;
	}
	*/
}
