package com.epam.jmp.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
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
	
	@Override
	public boolean isPersonExist(String email) {
		boolean result = false;
		if (StringUtils.isNotBlank(email)) {
			result = findByEmail(email).isPresent();
		}
		return result;
	}
	
	@Override
	public Optional<Person> findByEmail(String email) {
		return genericDAO.findByEmail(email);
	}
	
}
