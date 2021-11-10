package com.nttdata.nova.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.nova.bookStore.dto.BookDto;
import com.nttdata.nova.bookStore.dto.EditorialDto;
import com.nttdata.nova.bookStore.service.IBookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "BookRestService", description = "The Book api")
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private IBookService bookService;

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Insert book", description = "Inser book method", tags = { "BookRestService" })
	public HttpEntity<BookDto> insertEditorial(@RequestBody BookDto book) {
		BookDto bookDto = bookService.save(book);
		BookController.generateBookLinks(bookDto);
		EditorialController.generateEditorialLinks(bookDto.getEditorial());

		return new ResponseEntity<BookDto>(bookDto, HttpStatus.OK);
	}

	@PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update book", description = "Update book method", tags = { "BookRestService" })
	public HttpEntity<BookDto> updateEditorial(@RequestBody BookDto book) {
		BookDto bookDto = bookService.update(book);
		BookController.generateBookLinks(bookDto);
		EditorialController.generateEditorialLinks(bookDto.getEditorial());

		return new ResponseEntity<BookDto>(bookDto, HttpStatus.OK);
	}

	@GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get all books", description = "Get all books method", tags = { "BookRestService" })
	public HttpEntity<List<BookDto>> getAllBooks() {
		List<BookDto> bookDtoList = bookService.findAll();
		bookDtoList.forEach(b -> {
			BookController.generateBookLinks(b);
			EditorialController.generateEditorialLinks(b.getEditorial());
		});

		return new ResponseEntity<List<BookDto>>(bookDtoList, HttpStatus.OK);
	}

	@GetMapping(path = "/get/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Find a book by id", description = "Find a book by id method", tags = { "BookRestService" })
	public HttpEntity<BookDto> getBookById(@PathVariable("id") Long id) {
		BookDto bookDto = bookService.findById(id);
		BookController.generateBookLinks(bookDto);
		EditorialController.generateEditorialLinks(bookDto.getEditorial());

		return new ResponseEntity<BookDto>(bookDto, HttpStatus.OK);
	}

	@PostMapping(path = "/get/editorial", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Find a book by editorial", description = "Find a book by editorial method", tags = {
			"BookRestService" })
	public HttpEntity<List<BookDto>> getBooksByEditorial(@RequestBody EditorialDto editorial) {
		List<BookDto> bookDtoList = bookService.searchByEditorial(editorial);
		bookDtoList.forEach(b -> {
			BookController.generateBookLinks(b);
			EditorialController.generateEditorialLinks(b.getEditorial());
		});

		return new ResponseEntity<List<BookDto>>(bookDtoList, HttpStatus.OK);
	}

	@GetMapping(path = "/get/title/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Find a book by title", description = "Find a book by title method", tags = {
			"BookRestService" })
	public HttpEntity<List<BookDto>> getBooksByTitle(@PathVariable("title") String title) {
		List<BookDto> bookDtoList = bookService.searchByTitle(title);
		bookDtoList.forEach(b -> {
			BookController.generateBookLinks(b);
			EditorialController.generateEditorialLinks(b.getEditorial());
		});

		return new ResponseEntity<List<BookDto>>(bookDtoList, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{id}")
	@Operation(summary = "Delete book", description = "Delete book method", tags = { "BookRestService" })
	public HttpEntity<String> deleteBook(@PathVariable("id") Long id) {
		BookDto book = bookService.findById(id);
		bookService.delete(book);

		return new ResponseEntity<String>("Book with id=" + id + " was deleted", HttpStatus.OK);
	}

	public static void generateBookLinks(BookDto bookDto) {
		bookDto.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(bookDto.getId())).withSelfRel());
		bookDto.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(EditorialController.class).getEditorialById(bookDto.getEditorial().getId()))
				.withRel("editorial"));
	}
}
