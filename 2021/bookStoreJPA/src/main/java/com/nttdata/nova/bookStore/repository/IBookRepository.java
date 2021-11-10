package com.nttdata.nova.bookStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nttdata.nova.bookStore.entity.Book;
import com.nttdata.nova.bookStore.entity.Editorial;

@Repository
public interface IBookRepository extends CrudRepository<Book, Long>{
	
	@Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
	public List<Book> searchByTitle(@Param("title") String search);

	@Query("SELECT b FROM Book b WHERE b.editorial LIKE :editorial")
	public List<Book> searchByEditorial(@Param("editorial") Editorial search);
}
