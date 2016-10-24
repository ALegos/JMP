package com.epam.jmp.service;

import java.util.Optional;

import com.epam.jmp.model.Person;

public interface PersonService extends GenericService<Person> {
	
	boolean isPersonExist(String email);
	
	Optional<Person> findByEmail(String email);
	
}
