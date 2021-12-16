package com.example.redis_crud_listoperation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class RedisCrudListOperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisCrudListOperationApplication.class, args);
    }

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory(){
        return new LettuceConnectionFactory();
    }

    @Bean
    RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        return redisTemplate;
    }

}
