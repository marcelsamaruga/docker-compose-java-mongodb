package com.example.demo.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/person")
public class PersonController {
	
	private PersonService service;
	
	@Autowired
	public PersonController(PersonService service) {
		this.service = service;
	}

	@GetMapping("/all")
	public ResponseEntity<Page<Person>> findAll(Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}
	
	@PostMapping
	public ResponseEntity<Person> save(@RequestBody Person person) {
		return new ResponseEntity<Person>(service.save(person), HttpStatus.CREATED);
	}
	
}
