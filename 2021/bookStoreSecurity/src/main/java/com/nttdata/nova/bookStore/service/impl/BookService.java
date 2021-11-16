package com.nttdata.nova.bookStore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.nttdata.nova.bookStore.dto.BookDto;
import com.nttdata.nova.bookStore.dto.EditorialDto;
import com.nttdata.nova.bookStore.entity.Book;
import com.nttdata.nova.bookStore.entity.Editorial;
import com.nttdata.nova.bookStore.repository.IBookRepository;
import com.nttdata.nova.bookStore.service.IBookService;

@Service
public class BookService implements IBookService{
	
	@Autowired
	private IBookRepository bookRepository;
	
	@Override
	@CacheEvict(value="books", allEntries=true)
	@PreAuthorize("hasRole('ADMIN')")
	public BookDto save(BookDto bookDto) {
		bookDto.setId(null);
		return new BookDto(bookRepository.save(new Book(bookDto)));
	}

	@Override
	@CacheEvict(value="books", allEntries=true)
	@PreAuthorize("hasRole('ADMIN')")
	public BookDto update(BookDto bookDto) {	
		return new BookDto(bookRepository.save(new Book(bookDto)));	
	}

	@Override
	@CacheEvict(value="books", allEntries=true)
	@PreAuthorize("hasRole('ADMIN')")
	public void delete(BookDto bookDto) {
		bookRepository.delete(new Book(bookDto));
	}

	@Override
	@Cacheable("books")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public BookDto findById(Long id) {
		Optional<Book> book = bookRepository.findById(id);			
		return book.isPresent() ? new BookDto(book.get()) : null;
	}

	@Override
	@Cacheable("books")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public List<BookDto> findAll() {
		List<BookDto> bookDtoList = new ArrayList<BookDto>();
		
		List<Book> bookList =  (List<Book>) bookRepository.findAll();
		bookList.stream().forEach(b -> bookDtoList.add(new BookDto(b)));
		
		return bookDtoList;
	}

	
	@Override
	@Cacheable("books")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public List<BookDto> searchByTitle(String search) {
		List<BookDto> bookDtoList = new ArrayList<BookDto>();
		
		List<Book> bookList =  (List<Book>) bookRepository.searchByTitle(search);
		bookList.stream().forEach(b -> bookDtoList.add(new BookDto(b)));
		
		return bookDtoList;
	}

	@Override
	@Cacheable("books")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public List<BookDto> searchByEditorial(EditorialDto editorialDto) {
		List<BookDto> bookDtoList = new ArrayList<BookDto>();
		
		List<Book> bookList =  (List<Book>) bookRepository.searchByEditorial(new Editorial(editorialDto));
		bookList.stream().forEach(b -> bookDtoList.add(new BookDto(b)));
		
		return bookDtoList;
	}
	
}
