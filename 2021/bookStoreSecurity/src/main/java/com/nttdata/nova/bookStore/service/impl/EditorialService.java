package com.nttdata.nova.bookStore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.nttdata.nova.bookStore.dto.EditorialDto;
import com.nttdata.nova.bookStore.entity.Editorial;
import com.nttdata.nova.bookStore.repository.IEditorialRepository;
import com.nttdata.nova.bookStore.service.IEditorialService;

@Service
public class EditorialService implements IEditorialService {

	@Autowired
	private IEditorialRepository editorialRepository;

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@CacheEvict(value="editorials", allEntries=true)
	public EditorialDto save(EditorialDto editorialDto) {
		editorialDto.setId(null);
		return new EditorialDto(editorialRepository.save(new Editorial(editorialDto)));
	}

	@Override
	@CacheEvict(value="editorials", allEntries=true)
	@PreAuthorize("hasRole('ADMIN')")
	public EditorialDto update(EditorialDto editorialDto) {
		return new EditorialDto(editorialRepository.save(new Editorial(editorialDto)));
	}

	@Override
	@CacheEvict(value="editorials", allEntries=true)
	@PreAuthorize("hasRole('ADMIN')")
	public void delete(EditorialDto editorialDto) {
		editorialRepository.delete(new Editorial(editorialDto));
	}

	@Override
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	@Cacheable("editorials")
	public EditorialDto findById(Long id) {
		Optional<Editorial> editorial = editorialRepository.findById(id);
		return editorial.isPresent() ? new EditorialDto(editorial.get()) : null;
	}

	@Override
	@Cacheable("editorials")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public List<EditorialDto> findAll() {
		List<EditorialDto> editorialDtoList = new ArrayList<EditorialDto>();
		
		List<Editorial> editorialList =  (List<Editorial>) editorialRepository.findAll();
		editorialList.stream().forEach(e -> editorialDtoList.add(new EditorialDto(e)));
		
		return editorialDtoList;
	}

	@Override
	@Cacheable("editorials")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public List<EditorialDto> findByName(String name) {
		List<EditorialDto> editorialDtoList = new ArrayList<EditorialDto>();
		
		List<Editorial> editorialList =  (List<Editorial>) editorialRepository.findByName(name);
		editorialList.stream().forEach(e -> editorialDtoList.add(new EditorialDto(e)));
		
		return editorialDtoList;
	}
}
