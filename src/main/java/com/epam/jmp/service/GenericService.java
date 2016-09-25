package com.epam.jmp.service;

import java.util.List;

import com.epam.jmp.model.AbstractEntity;

public interface GenericService<T extends AbstractEntity> {

	T getByUid(String uid);

	List<T> getAll();

	T create(T t);

	T update(T t);

	void delete(String uid);
}
