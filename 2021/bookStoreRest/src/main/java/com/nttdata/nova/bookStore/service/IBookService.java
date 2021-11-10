package com.nttdata.nova.bookStore.service;

import java.util.List;

import com.nttdata.nova.bookStore.dto.BookDto;
import com.nttdata.nova.bookStore.dto.EditorialDto;

public interface IBookService {

	public BookDto save(BookDto book);
	public BookDto update(BookDto book);
	public void delete(BookDto book);
	public BookDto findById(Long id);
	public List<BookDto> findAll();
	
	public List<BookDto> searchByTitle(String search);
	public List<BookDto> searchByEditorial(EditorialDto search);
}
