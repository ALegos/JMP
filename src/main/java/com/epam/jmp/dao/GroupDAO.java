package com.epam.jmp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.jmp.model.Group;
import com.epam.jmp.persistance.PersistanceUnit;

@Repository
public class GroupDAO extends GenericDAO<Group>{

	@Autowired
	public GroupDAO(PersistanceUnit storage) {
		super(storage, Group.class);
	}

}
