package com.nttdata.nova.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.nova.bookStore.dto.EditorialDto;
import com.nttdata.nova.bookStore.exception.InvalidIdException;
import com.nttdata.nova.bookStore.service.IEditorialService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@Tag(name="EditorialRestService", description="The Editorial api")
@RequestMapping(value = "/editorial")
public class EditorialController {
	
	@Autowired
	private IEditorialService editorialService;
	
	@PostMapping(path = "/create", 
    consumes = MediaType.APPLICATION_JSON_VALUE, 
    produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Insert editorial", description = "Inser editorial method", tags = {"EditorialRestService"})
	public HttpEntity<EditorialDto> insertEditorial(@RequestBody EditorialDto editorial) {
		if (editorial.getId() != 0) {
			throw new InvalidIdException(editorial.getId());
		}
		
		EditorialDto editorialDto = editorialService.save(editorial);

		if(editorialDto!=null) {
			EditorialController.generateEditorialLinks(editorialDto);
		}

		return new ResponseEntity<EditorialDto>(editorialDto, HttpStatus.OK);
	}
	
	@PutMapping(path = "/update", 
	consumes = MediaType.APPLICATION_JSON_VALUE, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update editorial", description = "Update editorial method", tags = {"EditorialRestService"})
	public HttpEntity<EditorialDto> updateEditorial(@RequestBody EditorialDto editorial) {
		if (editorial.getId() == 0) {
			throw new InvalidIdException(editorial.getId());
		}
		
		EditorialDto editorialDto = editorialService.update(editorial);

		if(editorialDto!=null) {
			EditorialController.generateEditorialLinks(editorialDto);
		}
		
		return new ResponseEntity<EditorialDto>(editorialDto, HttpStatus.OK);
	}
	
	@GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get all editorial", description = "Get all editorial method", tags = {"EditorialRestService"})	
	public HttpEntity<List<EditorialDto>> getAllEditorial(){
		List<EditorialDto> editorialDtoList = editorialService.findAll();
		editorialDtoList.forEach(e ->{
			if(e!=null) {
				EditorialController.generateEditorialLinks(e);
			}
		});

		return new ResponseEntity<List<EditorialDto>>(editorialDtoList, HttpStatus.OK);
	}
	
	@GetMapping(path = "/get/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Find an editorial by id", description = "Find an editorial by id method", tags = {"EditorialRestService"})		
	public HttpEntity<EditorialDto> getEditorialById(@PathVariable("id") Long id){
		EditorialDto editorialDto = editorialService.findById(id);
		
		if(editorialDto!=null) {
			EditorialController.generateEditorialLinks(editorialDto);
		}
		
		return new ResponseEntity<EditorialDto>(editorialDto, HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/get/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Find an editorial by name", description = "Find an editorial by name method", tags = {"EditorialRestService"})	
	public HttpEntity<List<EditorialDto>> getEditorialByName(@PathVariable("name") String name){
		List<EditorialDto> editorialDtoList = editorialService.findByName(name);
		editorialDtoList.forEach(e -> {
			if(e!=null) {
				EditorialController.generateEditorialLinks(e);
			}
		});
		
		return new ResponseEntity<List<EditorialDto>>(editorialDtoList, HttpStatus.OK);
	}
	
	
	@DeleteMapping(path = "/delete/{id}")
	@Operation(summary = "Delete editorial", description = "Delete editorial method", tags = {"EditorialRestService"})
	public HttpEntity<String> deleteEditorial(@PathVariable("id") Long id){
		EditorialDto editorialDto = editorialService.findById(id);
		editorialService.delete(editorialDto);

		return new ResponseEntity<String>("Editorial with id="+id+" was deleted", HttpStatus.OK);
	}
	
	
	public static void generateEditorialLinks(EditorialDto editorialDto) {
		editorialDto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EditorialController.class).getEditorialById(editorialDto.getId())).withSelfRel());
	}
}
