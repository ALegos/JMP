package com.epam.jmp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.jmp.model.AbstractEntity;

public abstract class GenericDAO<T extends AbstractEntity> {
	
   private final Map<String, T> storage = new HashMap<>();
	
	public  T create (T t){
		storage.put(t.generateUid(), t);
		return t;
	}
	
	public void delete ( String uid){
		storage.remove(uid);
	}
	
	public T update (T t){
		storage.put(t.getUid(), t);
		return t;
	}
	
	public  T getByUid (String uid){
		return storage.get(uid);
	}
	
	public List<T> getAll (){
		return new ArrayList<T>(storage.values());
	}	
}
