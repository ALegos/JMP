package com.epam.jmp.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epam.jmp.model.Person;

@Repository
public class PersonDAO extends GenericDAO<Person> {
	
	public Optional<Person> findByEmail(String email) {
		return this.getAll().stream().filter(p -> email.equals(p.getEmail())).findFirst();
	}
}
