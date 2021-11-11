package com.nttdata.nova.bookStore.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.nttdata.nova.bookStore.entity.Book;
import com.nttdata.nova.bookStore.entity.Editorial;
import com.nttdata.nova.bookStore.repository.IBookRepository;

@DataJpaTest
@Sql("/createData.sql")
class BookRepositoryTest {
	
	@Autowired
	private IBookRepository bookRepository;

	@Test
	public void testInsert() {
		Editorial editorial = new Editorial("Nova editions");
		editorial.setId(Long.valueOf(1));

		Book book = new Book("Aprendiendo Spring Data JPA", "NTT Data", 
					Calendar.getInstance().getTime(), 100, 
					"Aprendiendo a programar microservicios con Spring Data JPA", editorial);

		book = bookRepository.save(book);
		assertNotNull(book);
	}
	
	@Test
	public void testFindAll() {
		List<Book> bookList = (List<Book>)bookRepository.findAll();
		assertNotNull(bookList);
	}
	
	@Test
	public void testFindById() {
		Optional<Book> book = bookRepository.findById(Long.valueOf(1));
		assertTrue(book.isPresent());
	}
	
	@Test
	public void testSearchByTitle() {
		List<Book> resultSearch = bookRepository.searchByTitle("microservicios");
		assertNotNull(resultSearch);
	}

	@Test
	public void testSearchByEditorial() {
		Editorial editorial = new Editorial("Nova editions");
		editorial.setId(Long.valueOf(1));
		
		List<Book> resultSearch = bookRepository.searchByEditorial(editorial);
		assertNotNull(resultSearch);
	}
	
	@Test
	public void testDelete() {
		bookRepository.delete(bookRepository.findById(Long.valueOf(1)).get());
		assertFalse(bookRepository.findById(Long.valueOf(1)).isPresent());
	}
	
	
}
