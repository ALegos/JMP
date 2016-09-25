package com.epam.jmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.jmp.dao.ManagerDAO;
import com.epam.jmp.model.Manager;
import com.epam.jmp.service.ManagerService;

@Service
public class ManagerServiceImpl extends GenericServiceImpl<Manager, ManagerDAO> implements ManagerService {

	@Autowired
	public ManagerServiceImpl(ManagerDAO managerDAO) {
		super(managerDAO);
	}

}
