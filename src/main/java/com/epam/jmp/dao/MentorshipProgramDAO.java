package com.epam.jmp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.jmp.model.MentorshipProgram;
import com.epam.jmp.persistance.PersistanceUnit;

@Repository
public class MentorshipProgramDAO extends GenericDAO<MentorshipProgram> {

	@Autowired
	public MentorshipProgramDAO(PersistanceUnit storage) {
		super(storage, MentorshipProgram.class);
	}
}
