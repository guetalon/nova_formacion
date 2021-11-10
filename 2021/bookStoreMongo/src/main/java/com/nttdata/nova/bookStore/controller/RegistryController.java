package com.nttdata.nova.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.nova.bookStore.dto.BookRegistryDto;
import com.nttdata.nova.bookStore.service.IBookRegistryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@Tag(name="RegistryController", description="The Registry api")
@RequestMapping(value = "/registry")
public class RegistryController {
	
	@Autowired
	private IBookRegistryService registryService;
	

	@GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get all registry", description = "Get all system registries", tags = {"RegistryController"})	
	public HttpEntity<List<BookRegistryDto>> getAll(){
		return new ResponseEntity<List<BookRegistryDto>>(registryService.findAll(), HttpStatus.OK);
	}
}
