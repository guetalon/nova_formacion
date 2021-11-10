package com.nttdata.nova.bookStore.exception;

public class InvalidDateException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidDateException() {
        super(String.format("The date must be before today"));
    }

}
