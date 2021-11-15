package com.nttdata.nova.bookStore.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {
	
    @Bean
    public LettuceConnectionFactory redisConnectionFactory(
    		@Value("${spring.redis.host}") String redisHost,
    		@Value("${spring.redis.port}") int redisPort) {
        return new LettuceConnectionFactory(redisHost, redisPort);
    }
    
    @Bean
    public RedisTemplate<?, ?> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}

