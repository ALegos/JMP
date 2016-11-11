package com.epam.jmp.dto.converters;

import org.hibernate.Hibernate;
import org.hibernate.collection.internal.AbstractPersistentCollection;
import org.hibernate.proxy.HibernateProxy;

public abstract class DTOConverter<D, E> {
	
	abstract public E toEntity(D dto);
	
	abstract public D toDTO(E entity);
	
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
