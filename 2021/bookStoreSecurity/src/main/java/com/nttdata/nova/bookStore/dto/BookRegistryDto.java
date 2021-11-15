package com.nttdata.nova.bookStore.dto;

import java.io.Serializable;
import java.util.Date;

import com.nttdata.nova.bookStore.collection.BookRegistry;

public class BookRegistryDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String message;

	private Date date;
	
	
	public BookRegistryDto() {
	}
	
	public BookRegistryDto(BookRegistry bookRegistry) {
		this.id = bookRegistry.getId();
		this.message = bookRegistry.getMessage();
		this.date = bookRegistry.getDate();
	}
	
	public BookRegistryDto(String message, Date date) {
		this.message = message;
		this.date = date;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
