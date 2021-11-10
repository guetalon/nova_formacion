package com.nttdata.nova.bookStore.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nttdata.nova.bookStore.entity.Editorial;


public class EditorialDto implements Serializable{

	private static final long serialVersionUID = 1L;


	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nombre")
	private String name;
	

	public EditorialDto() {
	}
	
	public EditorialDto(Editorial editorial) {
		this.id = editorial.getId();
		this.name = editorial.getName();
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
