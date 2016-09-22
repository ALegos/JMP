package com.epam.jmp.dao;

import java.util.List;

import com.epam.jmp.model.AbstractEntity;
import com.epam.jmp.persistance.PersistanceUnit;

public abstract class GenericDAO<T extends AbstractEntity> {

	private PersistanceUnit storage;
	private Class<T> clazz;
	
	public GenericDAO(PersistanceUnit storage, Class<T> clazz) {
		this.storage = storage;
		this.clazz = clazz;
	}
	
	public T create (T t){
		return storage.persist(clazz, t);
	}
	
	public void delete(long uid){
		storage.delete(clazz, uid);
	}
	
	public T update(T t){
		return storage.update(clazz, t);
	}
	
	public List<T> getAll(){
		return storage.findAll(clazz);
	}
	
	public T getByUid(long uid){
		return storage.find(clazz, uid);
	}
}
