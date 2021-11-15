package com.nttdata.nova.bookStore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nttdata.nova.bookStore.collection.BookRegistry;
import com.nttdata.nova.bookStore.dto.BookRegistryDto;
import com.nttdata.nova.bookStore.repository.IBookRegistryRepository;
import com.nttdata.nova.bookStore.service.IBookRegistryService;

@Service
public class BookRegistryService implements IBookRegistryService{
	
	@Autowired
	private IBookRegistryRepository methodRegistryRepository;
	

	@Override
	@CacheEvict(value="registries", allEntries=true)
	public BookRegistryDto save(BookRegistryDto methodRegistryDto) {
		methodRegistryDto.setId(Long.valueOf(methodRegistryRepository.findAll().size() + 1));
		
		return new BookRegistryDto(methodRegistryRepository.save(new BookRegistry(methodRegistryDto)));
	}

	@Override
	@Cacheable("registries")
	public List<BookRegistryDto> findAll() {
		List<BookRegistryDto> methosRegistryDtoList = new ArrayList<BookRegistryDto>();
		
		List<BookRegistry> methodRegistryList = (List<BookRegistry>)methodRegistryRepository.findAll();
		methodRegistryList.forEach(m -> methosRegistryDtoList.add(new BookRegistryDto(m)));
		
		return methosRegistryDtoList;
	}

}
