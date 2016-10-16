package com.epam.jmp.service.impl;

import java.util.List;

import com.epam.jmp.dao.GenericDAO;
import com.epam.jmp.model.AbstractEntity;
import com.epam.jmp.service.GenericService;

public abstract class GenericServiceImpl<T extends AbstractEntity, X extends GenericDAO<T>>
		implements GenericService<T> {
	
	protected final X genericDAO;
	
	public GenericServiceImpl(X genericDAO) {
		super();
		this.genericDAO = genericDAO;
	}
	
	@Override
	public T getByUid(String uid) {
		return this.genericDAO.getByUid(uid);
	}
	
	@Override
	public List<T> getAll() {
		return this.genericDAO.getAll();
	}
	
	@Override
	public void create(T t) {
		this.genericDAO.create(t);
	}
	
	@Override
	public T update(T t) {
		return this.genericDAO.update(t);
	}
	
	@Override
	public void delete(String uid) {
		this.genericDAO.delete(uid);
	}
	
}
