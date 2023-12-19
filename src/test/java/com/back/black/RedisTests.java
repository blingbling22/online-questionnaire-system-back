package com.back.black;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest // 先初始化spring容器 就可以获取spring对象
public class RedisTests {
//    @Resource
//    StringRedisTemplate stringRedisTemplate;
//
//    @Test
//    public void testSet(){
//        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//
//        operations.set("username","tom");
//        operations.set("password","123");
//    }
//
//    @Test
//    public void get(){
//        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//        System.out.println(operations.get("username"));
//    }

}
