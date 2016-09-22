package com.epam.jmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.jmp.dao.ManagerDAO;
import com.epam.jmp.model.Manager;
import com.epam.jmp.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService{

	@Autowired
	private ManagerDAO managerDAO;
	
	@Override
	public Manager getByUid(long uid) {
		return managerDAO.getByUid(uid);
	}

	@Override
	public List<Manager> getAll() {
		return managerDAO.getAll();
	}

	@Override
	public Manager create(Manager t) {
		return managerDAO.create(t);
	}

	@Override
	public Manager update(Manager t) {
		return managerDAO.update(t);
	}

	@Override
	public void delete(long uid) {
		managerDAO.delete(uid);
	}

}
