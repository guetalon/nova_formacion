package com.nttdata.nova.bookStore.entity;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BOOK")
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
	@Column(name="ID")	
	private Long id;
	
	@Column(name = "TITLE", nullable = false, length = 150)
	private String title;
	
	@Column(name = "AUTHOR", nullable = false, length = 150)
	private String author;
	
	@Column(name = "PUBLISH", nullable = false)
	private Date publish;
	
	@Column(name = "PAGES", nullable = false)
	private Integer pages;
	
	@Column(name = "DESCRIPTION", nullable = true, length = 500)
	private String description;
	

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "editorial_id", nullable = false)
	private Editorial editorial;
	
	public Book() {
	}
	
	public Book(String title, String author, Date publish, Integer pages, String description, Editorial editorial) {
		this.title = title;
		this.author = author;
		this.publish = publish;
		this.pages = pages;
		this.description = description;
		this.editorial = editorial;
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

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}
}
