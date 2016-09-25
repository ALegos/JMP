package com.epam.jmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.jmp.dao.GroupDAO;
import com.epam.jmp.model.Group;
import com.epam.jmp.service.GroupService;

@Service
public class GroupServiceImpl extends GenericServiceImpl<Group, GroupDAO> implements GroupService {

	@Autowired
	public GroupServiceImpl(GroupDAO groupDAO) {
		super(groupDAO);
	}

}
