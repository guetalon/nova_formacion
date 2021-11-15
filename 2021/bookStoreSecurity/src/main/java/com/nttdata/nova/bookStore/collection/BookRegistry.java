package com.nttdata.nova.bookStore.collection;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.nttdata.nova.bookStore.dto.BookRegistryDto;

@Document("BookRegistry")
public class BookRegistry implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private String message;

	private Date date;
	
	
	public BookRegistry() {
	}
	
	public BookRegistry(BookRegistryDto bookRegistryDto) {
		this.id = bookRegistryDto.getId();
		this.message = bookRegistryDto.getMessage();
		this.date = bookRegistryDto.getDate();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
