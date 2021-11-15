package com.nttdata.nova.bookStore.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.nttdata.nova.bookStore.dto.EditorialDto;
import com.nttdata.nova.bookStore.entity.Editorial;
import com.nttdata.nova.bookStore.repository.IBookRepository;

@SpringBootTest()
@EnableCaching
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class BookServiceCacheTest {
	
	@Autowired
	private IBookService bookService;
	
	@MockBean
	private IBookRepository mockBookRepository;


	@Test
	public void findByIdTest() {
		bookService.findById(Long.valueOf(1));
		bookService.findById(Long.valueOf(1));

        verify(mockBookRepository, times(1)).findById(Long.valueOf(1));
	}
	

	@Test
	public void findAllTest() {
		bookService.findAll();
		bookService.findAll();
       
		verify(mockBookRepository, times(1)).findAll();
	}
	
	
	@Test
	public void searchByTitle() {
		bookService.searchByTitle("Aprendiendo microservicios");
		bookService.searchByTitle("Aprendiendo microservicios");
				
		verify(mockBookRepository, times(1)).searchByTitle("Aprendiendo microservicios");	
	}

	
	@Test
	public void searchByEditorial() {
		Editorial editorial = new Editorial();
		editorial.setId(Long.valueOf(1));
		editorial.setName("Ediciones Nova");

		bookService.searchByEditorial(new EditorialDto(editorial));
		bookService.searchByEditorial(new EditorialDto(editorial));
				
		verify(mockBookRepository, times(1)).searchByEditorial(BDDMockito.any(Editorial.class));
	}
	
	
	
	
}