package com.enuocms.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhanxiaoping on 2020/3/8.
 * zhanxp@me.com
 */
@Component
public class RedisService {

    @Autowired
    StringRedisTemplate redisTemplate;

    public boolean set(String key, String value) {
        return this.set(key, value, null);
    }

    public boolean set(String key, String value, Long expire) {
        boolean result = redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
            redisConnection.set(stringRedisSerializer.serialize(key), stringRedisSerializer.serialize(value));
            return true;
        });
        if (result && expire != null) {
            this.expire(key, expire);
        }
        return result;
    }

    public boolean expire(String key, Long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    public String get(String key) {
        return redisTemplate.execute((RedisCallback<String>) redisConnection -> {
            RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
            byte[] bytes = redisConnection.get(stringRedisSerializer.serialize(key));
            return stringRedisSerializer.deserialize(bytes);
        });
    }

    public void delete(String key){
        if (!StringUtils.isEmpty(key)) {
            redisTemplate.delete(key);
        }
    }

}