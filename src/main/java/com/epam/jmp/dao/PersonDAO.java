package com.epam.jmp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.jmp.model.Person;
import com.epam.jmp.persistance.PersistanceUnit;

@Repository
public class PersonDAO extends GenericDAO<Person>{
	
	@Autowired
	public PersonDAO(PersistanceUnit storage) {
		super(storage, Person.class);
	}
}
