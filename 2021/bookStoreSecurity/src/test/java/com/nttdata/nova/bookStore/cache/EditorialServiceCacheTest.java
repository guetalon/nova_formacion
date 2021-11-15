package com.nttdata.nova.bookStore.cache;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.nttdata.nova.bookStore.repository.IEditorialRepository;
import com.nttdata.nova.bookStore.service.IEditorialService;

@SpringBootTest()
@EnableCaching
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class EditorialServiceCacheTest {
	
	@Autowired
	private IEditorialService editorialService;
	
	@MockBean
	private IEditorialRepository mockEditorialRepository;
	

	@Test
	public void findByIdTest() {	
		editorialService.findById(Long.valueOf(1));
		editorialService.findById(Long.valueOf(1));

        verify(mockEditorialRepository, times(1)).findById(Long.valueOf(1));
	}
	

	@Test
	public void findAllTest() {	
		editorialService.findAll();
		editorialService.findAll();
       
		verify(mockEditorialRepository, times(1)).findAll();
	}
	
	
	@Test
	public void findByNameTest() {
		editorialService.findByName("Ediciones Nova");
		editorialService.findByName("Ediciones Nova");
       
		verify(mockEditorialRepository, times(1)).findByName("Ediciones Nova");
	}
	
	
}

