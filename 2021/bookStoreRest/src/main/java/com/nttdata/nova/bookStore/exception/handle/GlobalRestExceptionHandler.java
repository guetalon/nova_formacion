package com.nttdata.nova.bookStore.exception.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nttdata.nova.bookStore.exception.InvalidIdException;


@ControllerAdvice
public class GlobalRestExceptionHandler{
	private static final Logger LOG = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);
	
	ExceptionResponse result;

	@ExceptionHandler(InvalidIdException.class)
	public HttpEntity<ExceptionResponse> invalidIdException(RuntimeException e) {
		result = new ExceptionResponse(EnumBusinessCodeError.BUSI_ID);
		
		LOG.error("Se ha producido un error de tipo InvalidIdException");
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}

}
