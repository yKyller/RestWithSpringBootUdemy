package br.com.erudio.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name= "Person Endpoint", description = "Endpoint para Persons")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController { 
	
	@Autowired
	private PersonServices services;
	
	@Operation(summary= "Find all people recorded")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<CollectionModel<PersonVO>> findAll(
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="size", defaultValue = "12") int size,
			@RequestParam(value="sort", defaultValue = "firstName,asc") String sort) {
		
		sort = sort.split(",").length < 2 ? "firstName,asc" : sort;
		
		var sortColumn = sort.split(",")[0];
		
		var sortDirection = "desc".equalsIgnoreCase(sort.split(",")[1]) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortColumn));
		
		Page<PersonVO> persons = services.findAll(pageable);
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		List<PersonVO> personsAll = services.findAll();
		int lastPage = (personsAll.size()-1)/5;
		Link firstPageLink = linkTo(methodOn(PersonController.class).findAll(0, size, sort)).withRel("first");
		Link previousPageLink = linkTo(methodOn(PersonController.class).findAll(page-1, size, sort)).withRel("previous");
		Link findAllLink = linkTo(methodOn(PersonController.class).findAll(page, size, sort)).withSelfRel();
		Link nextPageLink = linkTo(methodOn(PersonController.class).findAll(page+1, size, sort)).withRel("next");
		Link lastPageLink = linkTo(methodOn(PersonController.class).findAll(lastPage, size, sort)).withRel("last");
		if (page > 0 && page < lastPage) {
			return ResponseEntity.ok(CollectionModel.of(persons, firstPageLink, previousPageLink, findAllLink, nextPageLink, lastPageLink));
		} else if (page <= 0) {
			return ResponseEntity.ok(CollectionModel.of(persons, firstPageLink, findAllLink, nextPageLink, lastPageLink));
		} else if (page >= lastPage) {
			return ResponseEntity.ok(CollectionModel.of(persons, firstPageLink, previousPageLink, findAllLink, lastPageLink));
		} else {
			return ResponseEntity.ok(CollectionModel.of(persons, firstPageLink, lastPageLink));
		}
	}
	
	@Operation(summary= "Find all people recorded with token name")
	@GetMapping(value = "/findPersonByName/{firstName}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<CollectionModel<PersonVO>> findPersonByName(
			@PathVariable("firstName") String firstName,
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="size", defaultValue = "12") int size,
			@RequestParam(value="sort", defaultValue = "firstName,asc") String sort) {
		
		var sortDirection = "desc".equalsIgnoreCase(sort.split(",")[1]) ? Direction.DESC : Direction.ASC;
		
		var sortColumn = sort.split(",")[0];
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortColumn));
		
		Page<PersonVO> persons = services.findPersonByName(firstName, pageable);
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		return ResponseEntity.ok(CollectionModel.of(persons));
	}
	
	@Operation(summary= "Find a unique people recorded")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO personVO = services.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@Operation(summary= "Save one record of people")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO person) {
		PersonVO personVO = services.create(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@Operation(summary= "Update people recorded")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO person) {
		PersonVO personVO = services.update(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@Operation(summary= "Disable a specific person by your ID")
	@PatchMapping(value = "/disable/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO disablePerson(@PathVariable("id") Long id) {
		PersonVO personVO = services.disablePerson(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@Operation(summary= "Enable a specific person by your ID")
	@PatchMapping(value = "/enable/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO enablePerson(@PathVariable("id") Long id) {
		PersonVO personVO = services.enablePerson(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@Operation(summary= "Delete people recorded")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}
}
