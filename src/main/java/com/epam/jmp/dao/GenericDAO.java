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
		getManager().persist(t);
		getManager().flush();
		return t.getUid();
	}
	
	public void delete(String uid) {
		getManager().remove(getManager().getReference(getType(), uid));
	}
	
	public T update(T t) {
		return getManager().merge(t);
	}
	
	public T getByUid(String uid) {
		return getManager().find(getType(), uid);
	}
	
	public List<T> getAll() {
		final StringBuffer queryString = new StringBuffer("SELECT e from ");
		queryString.append(getType().getSimpleName()).append(" e ");
		TypedQuery<T> query = getManager().createQuery(queryString.toString(), getType());
		return query.getResultList();
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}
}
