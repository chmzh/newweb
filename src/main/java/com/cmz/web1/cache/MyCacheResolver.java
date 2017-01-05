package com.cmz.web1.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;

import com.cmz.web1.service.UserService;

public class MyCacheResolver implements CacheResolver {  
  
    @Autowired
    @Qualifier("cache1")
    private CacheManager cacheManager;  
  
    @Override  
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {  
        List<Cache> caches = new ArrayList<Cache>();  
        for(String cacheName : context.getOperation().getCacheNames()) {  
            caches.add(cacheManager.getCache(cacheName));  
        }  
        if(context.getTarget() instanceof UserService) {  
            caches.add(cacheManager.getCache("user"));
        }  
        return caches;  
    }  
} 
