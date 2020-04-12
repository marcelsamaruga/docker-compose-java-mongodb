package com.example.demo.person;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	private PersonRepository repository;
	
	@Autowired
	public PersonService(PersonRepository repository) {
		this.repository = repository;
	}
	
	public Person save(Person person) {
		return repository.save(person);
	}
	
	public Page<Person> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public Optional<Person> findByGuid(String guid) {
		return repository.findById(guid);
	}	
}
