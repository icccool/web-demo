package com.web.utils.redis;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtils implements Serializable { 

    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	private JedisPool jedisPool;

    /**
     * 通过key获取
     * @return
     */
    public String getString(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 通过key获取 
     * @return 
     */  
    public Object get(String key) {  
    	Jedis jedis = null;
        try {
        	jedis = jedisPool.getResource();
        	return SerializeUtil.unserialize(jedis.get(key).getBytes("ISO-8859-1"));
        }catch(Exception e){
        	logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }  

    /**  
     * 删除 
     * 
     * @param key 
     */  
    public void del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if(jedis.get(key) != null){
            	jedis.del(key);
            }
        }catch(Exception e){
        	logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    	
    }  


    /**
     * 保存缓存并设置缓存时间
     * @param key
     * @param seconds
     * @param value
     * @return boolean
     */
    public boolean setex(String key,String value,int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.setex(key, seconds, value);
            return true;
        }catch(Exception e){
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;

    }

    /**
     * 保存缓存
     * @param key
     * @param value
     * @return boolean
     */
    public boolean set(String key,String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return true;
    }

   /**
    * 保存/修改缓存
    * @param key
    * @param seconds
    * @param value
    * @return boolean
    */
    public boolean setKey(String key,int seconds,String value) {  
    	Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if(seconds>0){
            	jedis.setex(key, seconds, value);
            }else{
            	jedis.set(key,value);
            }
        }catch(Exception e){
        	logger.error(e.getMessage(), e);
        	return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return true;
    }

    /**
     * 删除缓存
     * @param key
     */
    public void deleteKey(String key) {  
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if(jedis.get(key) != null){
            	jedis.del(key);
            }
        }catch(Exception e){
        	logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    	
    }  
}  