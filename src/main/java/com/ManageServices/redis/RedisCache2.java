package com.ManageServices.redis;


import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
public class RedisCache2 implements Cache{
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id;
    private RedisTemplate redisTemplate;
    private static final long EXPIRE_TIME_IN_MINUTES = 30;
    public RedisCache2(String id){
        if(id == null){
            throw new IllegalArgumentException("Cache instance require an ID");
        }
        this.id = id;
    }

    private RedisTemplate getRedisTemplate(){
        if (redisTemplate == null){
            redisTemplate = (RedisTemplate) SpringUtils.getBean("redisTemplate");
        }
        return redisTemplate;
    }
    @Override
    public String getId(){
        return id;
    }

    @Override
    public void putObject(Object key,Object value){
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key,value,EXPIRE_TIME_IN_MINUTES,TimeUnit.MINUTES);
    }

    @Override
    public Object getObject(Object key){
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        return opsForValue.get(key);
    }

    @Override
    public Object removeObject(Object key){
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.delete(key);
        return null;
    }

    @Override
    public void clear(){
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.execute((RedisCallback)connection->{
            connection.flushDb();
            return null;
        });

    }

    @Override
    public int getSize(){
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock(){
        return readWriteLock;
    }
}
