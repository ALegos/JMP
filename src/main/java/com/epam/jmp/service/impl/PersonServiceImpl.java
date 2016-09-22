package com.epam.jmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.jmp.dao.PersonDAO;
import com.epam.jmp.model.Person;
import com.epam.jmp.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonDAO personDAO;
	
	@Override
	public Person getByUid(long uid) {
		return personDAO.getByUid(uid);
	}

	@Override
	public List<Person> getAll() {
		return personDAO.getAll();
	}

	@Override
	public Person create(Person t) {
		return personDAO.create(t);
	}

	@Override
	public Person update(Person t) {
		return personDAO.update(t);
	}

	@Override
	public void delete(long uid) {
		personDAO.delete(uid);
	}

}
