package com.nttdata.nova.bookStore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.nova.bookStore.entity.Editorial;

@Repository
public interface IEditorialRepository extends CrudRepository<Editorial, Long>{
	
	public List<Editorial> findByName(String name);

}
