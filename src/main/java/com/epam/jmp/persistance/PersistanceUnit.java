package com.epam.jmp.persistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.jmp.model.AbstractEntity;

public class PersistanceUnit {
	
	private final Map<Class, Map<Long, Object>> storage = new HashMap<>();
	
	public <T extends AbstractEntity> void init (Class<T> tClass){
		storage.put(tClass, new HashMap<>());
	}
	
	public <T extends AbstractEntity> T persist (Class<T> tClass, T t){
		storage.get(tClass).put(t.generateUid(), t);
		return t;
	}
	
	public <T extends AbstractEntity> void delete (Class<T> tClass, long uid){
		storage.get(tClass).remove(uid);
	}
	
	public <T extends AbstractEntity> T update (Class<T> tClass, T t){
		storage.get(tClass).put(t.getUid(), t);
		return t;
		
	}
	
	public <T extends AbstractEntity> T find (Class<T> tClass, long uid){
		return (T) storage.get(tClass).get(uid);
	}
	
	public <T extends AbstractEntity> List<T> findAll (Class<T> tClass){
		List<T> resultList = new ArrayList<>();
		storage.get(tClass).entrySet().forEach(x -> resultList.add((T) x));
		return resultList;
	}	
	
	
	

}
