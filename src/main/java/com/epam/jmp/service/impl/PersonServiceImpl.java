package com.epam.jmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.jmp.dao.PersonDAO;
import com.epam.jmp.model.Person;
import com.epam.jmp.service.PersonService;

@Service
public class PersonServiceImpl extends GenericServiceImpl<Person, PersonDAO> implements PersonService {

	@Autowired
	public PersonServiceImpl(PersonDAO genericDAO) {
		super(genericDAO);
	}

}
