package com.nttdata.nova.bookStore.cache;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import redis.embedded.RedisServer;



@Configuration
public class RedisEmbededConfiguration {

	private RedisServer redisServer;

	public RedisEmbededConfiguration(@Value("${spring.redis.port}") int redisPort) {
		this.redisServer = RedisServer.builder().setting("maxheap 512Mb").port(redisPort).build();
	}

	@PostConstruct
	public void postConstruct() {
		  redisServer.start();
	}

	@PreDestroy
	public void preDestroy() {
		redisServer.stop();
	}
}
