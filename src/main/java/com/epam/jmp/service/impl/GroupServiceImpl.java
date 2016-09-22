package com.epam.jmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.jmp.dao.GroupDAO;
import com.epam.jmp.model.Group;
import com.epam.jmp.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService{

	@Autowired
	private GroupDAO groupDAO;
	
	@Override
	public Group getByUid(long uid) {
		return groupDAO.getByUid(uid);
	}

	@Override
	public List<Group> getAll() {
		return groupDAO.getAll();
	}

	@Override
	public Group create(Group t) {
		return groupDAO.create(t);
	}

	@Override
	public Group update(Group t) {
		return groupDAO.update(t);
	}

	@Override
	public void delete(long uid) {
		groupDAO.delete(uid);
	}

}
