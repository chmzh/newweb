package com.cmz.web1.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class CacheManager {

	public SimpleCacheManager cacheManager(){
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		List<Cache> caches = new ArrayList<>();
		//Cache cache = new ConcurrentMapCacheFactoryBean();
		//caches.add();
		cacheManager.setCaches(caches);
		cacheManager.getCache("test");
		return cacheManager;
	}
}
