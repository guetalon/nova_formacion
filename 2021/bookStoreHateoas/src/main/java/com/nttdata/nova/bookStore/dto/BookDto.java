package com.nttdata.nova.bookStore.dto;



import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nttdata.nova.bookStore.entity.Book;


public class BookDto extends RepresentationModel<BookDto> implements Serializable{

	private static final long serialVersionUID = 1L;


	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("titulo")
	private String title;

	@JsonProperty("autor")
	private String author;
	
	@JsonProperty("publicado")
	private Date publish;

	@JsonProperty("paginas")
	private Integer pages;
	
	@JsonProperty("descripcion")
	private String description;
	
	@JsonProperty("editorial")
	private EditorialDto editorial;
	
	public BookDto() {
	}
	
	public BookDto(Book book) {
		this.id = book.getId();
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.publish = book.getPublish();
		this.pages = book.getPages();
		this.description = book.getDescription();
		this.editorial = new EditorialDto(book.getEditorial());
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPublish() {
		return publish;
	}
	public void setPublish(Date publish) {
		this.publish = publish;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EditorialDto getEditorial() {
		return editorial;
	}

	public void setEditorial(EditorialDto editorial) {
		this.editorial = editorial;
	}
}
