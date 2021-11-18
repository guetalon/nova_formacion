package com.nttdata.nova.bookStore.controller;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.nova.bookStore.dto.BookDto;
import com.nttdata.nova.bookStore.dto.EditorialDto;
import com.nttdata.nova.bookStore.exception.InvalidDateException;
import com.nttdata.nova.bookStore.exception.InvalidEditorialException;
import com.nttdata.nova.bookStore.exception.InvalidIdException;
import com.nttdata.nova.bookStore.exception.handle.EnumBusinessCodeError;
import com.nttdata.nova.bookStore.exception.handle.ExceptionResponse;
import com.nttdata.nova.bookStore.service.IBookService;
import com.nttdata.nova.bookStore.service.IEditorialService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

@RestController
@Tags({
		@Tag(name = "BookRestServiceRead", description = "The read Book api"),
		@Tag(name = "BookRestServiceWrite", description = "The write Book api") })
@RequestMapping(value = "/book")
public class BookController {
	
	private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private IBookService bookService;

	@Autowired
	private IEditorialService editorialService;
	
	
	@ExceptionHandler(InvalidDateException.class)
	public HttpEntity<ExceptionResponse> invalidDateException(Exception e) {
		ExceptionResponse result = new ExceptionResponse(EnumBusinessCodeError.BUSI_DATE);
		
		LOG.error("Se ha producido un error de tipo InvalidDateException");
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(InvalidEditorialException.class)
	public HttpEntity<ExceptionResponse> invalidEditorialException(Exception e) {
		ExceptionResponse result = new ExceptionResponse(EnumBusinessCodeError.BUSI_EDITORIAL);
		
		LOG.error("Se ha producido un error de tipo InvalidEditorialException");
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Insert book", description = "Inser book method", tags = { "BookRestServiceWrite" })
	public HttpEntity<BookDto> insertBook(@RequestBody BookDto book) {
		if (book.getId() != 0) {
			throw new InvalidIdException(book.getId());
		}

		if (book.getPublish().after(Calendar.getInstance().getTime())) {
			throw new InvalidDateException();
		}

		if (editorialService.findById(book.getEditorial().getId()) == null) {
			throw new InvalidEditorialException();
		}

		BookDto bookDto = bookService.save(book);

		return new ResponseEntity<BookDto>(bookDto, HttpStatus.OK);
	}

	@PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update book", description = "Update book method", tags = { "BookRestServiceWrite" })
	public HttpEntity<BookDto> updateBook(@RequestBody BookDto book) {
		if (book.getId() == 0) {
			throw new InvalidIdException(book.getId());
		}

		if (book.getPublish().after(Calendar.getInstance().getTime())) {
			throw new InvalidDateException();
		}

		if (editorialService.findById(book.getEditorial().getId()) == null) {
			throw new InvalidEditorialException();
		}

		return new ResponseEntity<BookDto>(bookService.update(book), HttpStatus.OK);
	}

	@GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get all books", description = "Get all books method", tags = { "BookRestServiceRead" })
	public HttpEntity<List<BookDto>> getAllBooks() {
		return new ResponseEntity<List<BookDto>>(bookService.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/get/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Find a book by id", description = "Find a book by id method", tags = { "BookRestServiceRead" })
	public HttpEntity<BookDto> getBookById(@PathVariable("id") Long id) {
		return new ResponseEntity<BookDto>(bookService.findById(id), HttpStatus.OK);
	}

	@PostMapping(path = "/get/editorial", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Find a book by editorial", description = "Find a book by editorial method", tags = {
			"BookRestServiceRead" })
	public HttpEntity<List<BookDto>> getBooksByEditorial(@RequestBody EditorialDto editorial) {
		return new ResponseEntity<List<BookDto>>(bookService.searchByEditorial(editorial), HttpStatus.OK);
	}

	@GetMapping(path = "/get/title/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Find a book by title", description = "Find a book by title method", tags = {
			"BookRestServiceRead" })
	public HttpEntity<List<BookDto>> getBooksByTitle(@PathVariable("title") String title) {
		return new ResponseEntity<List<BookDto>>(bookService.searchByTitle(title), HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{id}")
	@Operation(summary = "Delete book", description = "Delete book method", tags = { "BookRestServiceWrite" })
	public HttpEntity<String> deleteBook(@PathVariable("id") Long id) {
		BookDto book = bookService.findById(id);
		bookService.delete(book);

		return new ResponseEntity<String>("Book with id=" + id + " was deleted", HttpStatus.OK);
	}
}
