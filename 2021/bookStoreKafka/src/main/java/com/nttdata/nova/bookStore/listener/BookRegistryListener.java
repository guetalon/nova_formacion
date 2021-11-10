package com.nttdata.nova.bookStore.listener;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.nttdata.nova.bookStore.dto.BookRegistryDto;
import com.nttdata.nova.bookStore.service.impl.BookRegistryService;

@Component
public class BookRegistryListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(BookRegistryListener.class);
	
	@Autowired
	private BookRegistryService bookRegistryService;
	
	@KafkaListener(topics = "${kafka.topic.name}")
	public void listener(@Payload String message, @Headers MessageHeaders headers) {
		bookRegistryService.save(new BookRegistryDto(message, Calendar.getInstance().getTime()));
		
		LOG.info("Mensaje recibido y procesado correctamente");
	}

}


