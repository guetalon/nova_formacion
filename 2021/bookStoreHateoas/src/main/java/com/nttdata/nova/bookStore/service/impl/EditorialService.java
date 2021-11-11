package com.nttdata.nova.bookStore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public EditorialDto save(EditorialDto editorialDto) {
		editorialDto.setId(null);
		return new EditorialDto(editorialRepository.save(new Editorial(editorialDto)));
	}

	@Override
	public EditorialDto update(EditorialDto editorialDto) {
		return new EditorialDto(editorialRepository.save(new Editorial(editorialDto)));
	}

	@Override
	public void delete(EditorialDto editorialDto) {
		editorialRepository.delete(new Editorial(editorialDto));
	}

	@Override
	public EditorialDto findById(Long id) {
		Optional<Editorial> editorial = editorialRepository.findById(id);
		return editorial.isPresent() ? new EditorialDto(editorial.get()) : null;
	}

	@Override
	public List<EditorialDto> findAll() {
		List<EditorialDto> editorialDtoList = new ArrayList<EditorialDto>();
		
		List<Editorial> editorialList =  (List<Editorial>) editorialRepository.findAll();
		editorialList.stream().forEach(e -> editorialDtoList.add(new EditorialDto(e)));
		
		return editorialDtoList;
	}

	@Override
	public List<EditorialDto> findByName(String name) {
		List<EditorialDto> editorialDtoList = new ArrayList<EditorialDto>();
		
		List<Editorial> editorialList =  (List<Editorial>) editorialRepository.findByName(name);
		editorialList.stream().forEach(e -> editorialDtoList.add(new EditorialDto(e)));
		
		return editorialDtoList;
	}
}
