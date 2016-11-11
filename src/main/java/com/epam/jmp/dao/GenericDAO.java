package com.epam.jmp.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.jmp.model.AbstractEntity;

@Repository
@Transactional
public abstract class GenericDAO<T extends AbstractEntity> {
	
	@PersistenceContext
	private EntityManager manager;
	
	private Class<T> type;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDAO() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		setType((Class) pt.getActualTypeArguments()[0]);
	}
	
	public String create(T t) {
		getEntityManager().persist(t);
		getEntityManager().flush();
		return t.getUid();
	}
	
	public void delete(String uid) {
		getEntityManager().remove(getEntityManager().getReference(getType(), uid));
	}
	
	public T update(T t) {
		return getEntityManager().merge(t);
	}
	
	public T getByUid(String uid) {
		return getEntityManager().find(getType(), uid);
	}
	
	public List<T> getAll() {
		final StringBuffer queryString = new StringBuffer("SELECT e from ");
		queryString.append(getType().getSimpleName()).append(" e ");
		TypedQuery<T> query = getEntityManager().createQuery(queryString.toString(), getType());
		return query.getResultList();
	}
	
	public EntityManager getEntityManager() {
		return manager;
	}
	
	public void setEntityManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public Class<T> getType() {
		return type;
	}
	
	public void setType(Class<T> type) {
		this.type = type;
	}
}
