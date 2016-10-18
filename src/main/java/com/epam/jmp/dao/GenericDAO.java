package com.epam.jmp.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.jmp.model.AbstractEntity;

@Repository
@Transactional
public abstract class GenericDAO<T extends AbstractEntity> {
	
	private final Map<String, T> storage = new HashMap<>();
	@PersistenceContext
	private EntityManager manager;
	
	private Class<T> type;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDAO() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}
	
	public void create(T t) {
		manager.persist(t);
	}
	
	public void delete(String uid) {
		manager.remove(manager.getReference(type, uid));
	}
	
	public T update(T t) {
		return manager.merge(t);
	}
	
	public T getByUid(String uid) {
		return manager.find(type, uid);
	}
	
	public List<T> getAll() {
		final StringBuffer queryString = new StringBuffer("SELECT e from ");
		queryString.append(type.getSimpleName()).append(" e ");
		TypedQuery<T> query = manager.createQuery(queryString.toString(), type);
		return query.getResultList();
	}
}
