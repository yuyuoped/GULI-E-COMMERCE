package com.yuyuoped.gmall.index.aspect;

import com.alibaba.fastjson.JSON;
import com.yuyuoped.gmall.index.annotation.GmallCache;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class GmallCacheAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RBloomFilter<String> bloomFilter;

    @Pointcut("@annotation(com.yuyuoped.gmall.index.annotation.GmallCache)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //获取方法签名
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        //获取方法
        Method method = signature.getMethod();
        //获取方法上的注解
        GmallCache gmallCache = method.getAnnotation(GmallCache.class);

        // 方法形参
        Object[] args = pjp.getArgs();
        // 获取缓存前缀
        String prefix = gmallCache.prefix();
        // 缓存的key
        String argsString = StringUtils.join(args, ",");
        String key = prefix + argsString;


        // 通过布隆过滤器判断数据是否存在，不存在则直接返回空
        if (!bloomFilter.contains(key)) {
            return null;
        }

        // 1.先查询缓存，如果缓存中命中则直接返回
        String json = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(json)) {
            return JSON.parse(json);
        }


        // 2.为了防止缓存击穿，添加分布式锁
        RLock fairLock = redissonClient.getFairLock(key);
        fairLock.lock();

        try {
            // 3.当前请求获取锁的过程中，可能有其他请求已经把数据放入缓存，此时，可以再次查询缓存，如果命中则直接返回
            String json2 = redisTemplate.opsForValue().get(key);
            if (StringUtils.isNotBlank(json2)) {
                return JSON.parse(json2);
            }
            // 4.执行目标方法，从数据库中获取数据
            Object result = pjp.proceed();
            // 5.把数据放入缓存并释放分布式锁
            int timeout = gmallCache.timeout();
            redisTemplate.opsForValue().set(key, JSON.toJSONString(result), timeout, TimeUnit.SECONDS);
            return result;
        } finally {
            fairLock.unlock();
        }
    }
}
