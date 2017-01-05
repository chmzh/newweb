package com.cmz.web1.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import com.google.code.ssm.api.format.SerializationType;
import com.google.code.ssm.providers.CacheException;


public class MemcachedCache implements Cache{  
    
    private com.google.code.ssm.Cache cache;  
      
    public com.google.code.ssm.Cache getCache() {  
        return cache;  
    }  
  
    public void setCache(com.google.code.ssm.Cache cache) {  
        this.cache = cache;  
    }  
  
    @Override  
    public String getName() {  
        // TODO Auto-generated method stub  
        return this.cache.getName();  
    }  
  
    @Override  
    public Object getNativeCache() {  
        // TODO Auto-generated method stub  
        return this.cache;  
    }  
  
    @Override  
    public ValueWrapper get(Object key) {  
        // TODO Auto-generated method stub  
        Object object = null;  
        try {  
            object = this.cache.get((String)key, SerializationType.JAVA);  
        } catch (TimeoutException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (CacheException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return (object != null ? new SimpleValueWrapper(object) : null);  
    }  
  
    @Override  
    public void put(Object key, Object value) {  
        // TODO Auto-generated method stub  
        try {  
            this.cache.set((String)key, 86400, value, SerializationType.JAVA);  
        } catch (TimeoutException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (CacheException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
  
    @Override  
    public void evict(Object key) {  
        // TODO Auto-generated method stub  
        try {  
            this.cache.delete((String)key);  
        } catch (TimeoutException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (CacheException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
  
    @Override  
    public void clear() {  
        // TODO Auto-generated method stub  
        try {  
            this.cache.flush();  
        } catch (TimeoutException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (CacheException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }

	@Override
	public <T> T get(Object key, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}  
  
      
  
} 
