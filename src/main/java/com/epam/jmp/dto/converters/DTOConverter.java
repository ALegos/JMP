package com.epam.jmp.dto.converters;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.Hibernate;
import org.hibernate.collection.internal.AbstractPersistentCollection;
import org.hibernate.proxy.HibernateProxy;
import org.modelmapper.ModelMapper;

public abstract class DTOConverter<D, E> {
	
	protected ModelMapper mapper;
	protected Class<D> dtoClass;
	protected Class<E> entityClass;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DTOConverter(ModelMapper mapper) {
		this.mapper = mapper;
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		this.dtoClass = ((Class) pt.getActualTypeArguments()[0]);
		this.entityClass = ((Class) pt.getActualTypeArguments()[1]);
	}
	
	public E toEntity(D dto) {
		E entity = this.mapper.map(dto, entityClass);
		return populateEntity(dto, entity);
	}
	
	public E mergeToEntity(D dto, E entity) {
		this.mapper.map(dto, entity);
		return populateEntity(dto, entity);
	}
	
	abstract protected E populateEntity(D dto, E entity);
	
	public D toDTO(E entity) {
		D dto = this.mapper.map(entity, dtoClass);
		return populateDTO(entity, dto);
	}
	
	public D mergeToDTO(E entity, D dto) {
		this.mapper.map(entity, dto);
		return populateDTO(entity, dto);
	}
	
	abstract protected D populateDTO(E entity, D dto);
	
	protected boolean isLoaded(Object attr) {
		if (attr instanceof HibernateProxy) {
			if (((HibernateProxy) attr).getHibernateLazyInitializer().isUninitialized()) {
				return false;
			}
		} else if (attr instanceof AbstractPersistentCollection) {
			if (!Hibernate.isInitialized(attr)) {
				return false;
			}
		}
		return (attr != null);
	};
	
}
