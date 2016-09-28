package com.epam.jmp.service;

import java.util.List;

import com.epam.jmp.model.MentorshipProgram;

public interface MentorshipProgramService extends GenericService<MentorshipProgram> {
	
	List<MentorshipProgram> findByOfficeLocation(String office);
	
}
