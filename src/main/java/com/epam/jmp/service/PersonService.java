package com.epam.jmp.service;

import java.util.List;
import java.util.Optional;

import com.epam.jmp.model.Person;

public interface PersonService extends GenericService<Person> {
	
	boolean isPersonExist(String email);
	
	Optional<Person> findByEmail(String email);
	
	public List<Person> findMentorsWithMoreThanSpecifiedMentees(Boolean status, Integer number);
}
