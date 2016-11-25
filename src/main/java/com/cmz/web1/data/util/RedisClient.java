package com.cmz.web1.data.util;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisClient<K,V> {
	private JedisConnectionFactory jedisConnectionFactory;
	private StringRedisSerializer keySerializer;
	private boolean enableTransactionSupport;
	private RedisTemplate<K,V> redisTemplate;
	
	public void init(){
		redisTemplate = new RedisTemplate<K, V>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		redisTemplate.setKeySerializer(keySerializer);
		redisTemplate.setValueSerializer(keySerializer);
		redisTemplate.setEnableDefaultSerializer(enableTransactionSupport);
		redisTemplate.afterPropertiesSet();
	}
	
	public V getString(K key){
		//redisTemplate.opsForValue().set(key, value);
	
		return redisTemplate.opsForValue().get(key);
	}
	public void setString(K key,V val){
		redisTemplate.opsForValue().append(key, (String)val);
	}
	
	public V getObject(K key,Object hashKey){
		return (V) redisTemplate.opsForHash().get(key, hashKey);
	}
	
	public void setObject(K key,Object hashKey,V val){
		redisTemplate.opsForHash().put(key, hashKey, val);
		
	}
	
	public StringRedisSerializer getKeySerializer() {
		return keySerializer;
	}
	public void setKeySerializer(StringRedisSerializer keySerializer) {
		this.keySerializer = keySerializer;
	}
	public boolean isEnableTransactionSupport() {
		return enableTransactionSupport;
	}
	public void setEnableTransactionSupport(boolean enableTransactionSupport) {
		this.enableTransactionSupport = enableTransactionSupport;
	}




	public JedisConnectionFactory getJedisConnectionFactory() {
		return jedisConnectionFactory;
	}




	public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
		this.jedisConnectionFactory = jedisConnectionFactory;
	}
}
