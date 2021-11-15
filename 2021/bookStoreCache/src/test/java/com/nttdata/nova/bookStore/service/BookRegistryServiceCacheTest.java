package com.nttdata.nova.bookStore.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.nttdata.nova.bookStore.dto.BookRegistryDto;
import com.nttdata.nova.bookStore.repository.IBookRegistryRepository;

@SpringBootTest()
@EnableCaching
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class BookRegistryServiceCacheTest {
	
	@Autowired
	private IBookRegistryService bookRegistryService;
	
	@MockBean
	private IBookRegistryRepository mockBookRegistryRepository;
	
	@Autowired 
	private CacheManager manager;
	
	
	@Test
	public void save() {	
		bookRegistryService.save(new BookRegistryDto());
		
		manager.getCache("registries");
	}
	

	@Test
	public void findAllTest() {	
		bookRegistryService.findAll();
		bookRegistryService.findAll();

		verify(mockBookRegistryRepository, times(1)).findAll();
	}

}

