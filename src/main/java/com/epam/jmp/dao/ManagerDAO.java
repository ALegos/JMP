package com.epam.jmp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.epam.jmp.model.Manager;
import com.epam.jmp.persistance.PersistanceUnit;

@Repository
public class ManagerDAO extends GenericDAO<Manager> {

	@Autowired
	public ManagerDAO(PersistanceUnit storage) {
		super(storage, Manager.class);
	}
	
}
