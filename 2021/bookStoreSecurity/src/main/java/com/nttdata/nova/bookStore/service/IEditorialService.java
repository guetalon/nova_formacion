package com.nttdata.nova.bookStore.service;

import java.util.List;

import com.nttdata.nova.bookStore.dto.EditorialDto;

public interface IEditorialService {
	
	public EditorialDto save(EditorialDto editorial);
	public EditorialDto update(EditorialDto editorial);
	public void delete(EditorialDto editorial);
	public EditorialDto findById(Long id);
	public List<EditorialDto> findAll();

	public List<EditorialDto> findByName(String name);
}
