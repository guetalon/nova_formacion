package com.nttdata.nova.bookStore.service;

import java.util.List;

import com.nttdata.nova.bookStore.dto.BookRegistryDto;

public interface IBookRegistryService {

	public BookRegistryDto save (BookRegistryDto methodRegistryDto);
	public List<BookRegistryDto> findAll ();
}
