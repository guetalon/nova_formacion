package com.nttdata.nova.bookStore.exception;

public class InvalidEditorialException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidEditorialException() {
        super(String.format("Invalid editorial"));
    }
}
