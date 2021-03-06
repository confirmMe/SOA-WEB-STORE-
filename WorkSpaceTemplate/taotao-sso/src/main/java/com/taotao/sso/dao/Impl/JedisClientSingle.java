package com.taotao.sso.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.sso.dao.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientSingle implements JedisClient{
    @Autowired
    private JedisPool jedisPool;
    
    
	public String get(String key) {
		// TODO Auto-generated method stub
		Jedis jedis=jedisPool.getResource();
		String result=jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public String set(String key, String value) {
		// TODO Auto-generated method stub
		Jedis jedis=jedisPool.getResource();
		String result=jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String hkey, String key) {
		// TODO Auto-generated method stub
		Jedis jedis=jedisPool.getResource();
	    String result=jedis.hget(hkey, key);
	    jedis.close();
		return result;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		// TODO Auto-generated method stub
		Jedis jedis=jedisPool.getResource();
		long result=jedis.hset(hkey, key, value);
		jedis.close();
		return result;
	}

	@Override
	public long incr(String key) {
		Jedis jedis=jedisPool.getResource();
		long result=jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public long expire(String key, int second) {
		Jedis jedis=jedisPool.getResource();
		long result=jedis.expire(key, second);
		jedis.close();
		return result;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis=jedisPool.getResource();
		long result=jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public long del(String key) {
		Jedis jedis=jedisPool.getResource();
		long result=jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public long hdel(String hkey, String key) {
		Jedis jedis=jedisPool.getResource();
		long result=jedis.hdel(hkey, key);
		jedis.close();
		return result;
	}
	 

}
