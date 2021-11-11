package com.nttdata.nova.bookStore.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.nttdata.nova.bookStore.entity.Editorial;
import com.nttdata.nova.bookStore.repository.IEditorialRepository;

@DataJpaTest
@Sql("/createData.sql")
class EditorialRepositoryTest {
	
	@Autowired
	private IEditorialRepository editorialRepository;

	@Test
	public void testInsert() {
		Editorial editorial = new Editorial("Spring editions");
		editorial = editorialRepository.save(editorial);
		assertNotNull(editorial);
	}
	
	@Test
	public void testFindAll() {
		List<Editorial> editorialList = (List<Editorial>)editorialRepository.findAll();
		assertNotNull(editorialList);
	}
	
	@Test
	public void testFindById() {
		Optional<Editorial> editorial = editorialRepository.findById(Long.valueOf(1));
		assertTrue(editorial.isPresent());
	}
	
	@Test
	public void testDelete() {
		editorialRepository.delete(editorialRepository.findById(Long.valueOf(1)).get());
		assertFalse(editorialRepository.findById(Long.valueOf(1)).isPresent());
	}
	
	@Test
	public void testFindByName(){
		List<Editorial> editorialSearch = editorialRepository.findByName("Nova");
		assertNotNull(editorialSearch);
	}
}
