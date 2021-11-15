package com.nttdata.nova.bookStore.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.annotation.EnableCaching;

import com.nttdata.nova.bookStore.repository.IBookRegistryRepository;

@SpringBootTest()
@EnableCaching
public class BookRegistryServiceCacheTest {
	
	@Autowired
	private IBookRegistryService bookRegistryService;
	
	@MockBean
	private IBookRegistryRepository mockBookRegistryRepository;
	

	@Test
	public void findAllTest() {	
		bookRegistryService.findAll();
		bookRegistryService.findAll();

		verify(mockBookRegistryRepository, times(1)).findAll();
	}

}

