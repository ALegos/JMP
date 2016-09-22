package com.epam.jmp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.jmp.model.Lecture;
import com.epam.jmp.persistance.PersistanceUnit;

@Repository
public class LectureDAO extends GenericDAO<Lecture>{

	@Autowired
	public LectureDAO(PersistanceUnit storage) {
		super(storage, Lecture.class);
	} 

}
