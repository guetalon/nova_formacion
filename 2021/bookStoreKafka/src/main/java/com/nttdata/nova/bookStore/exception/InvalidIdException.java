package com.nttdata.nova.bookStore.exception;

public class InvalidIdException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidIdException(Long value) {
        super(String.format("%d has an invalid value", value));
    }
	
}
