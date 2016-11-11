package com.epam.jmp.service;

import java.util.Date;
import java.util.List;

import com.epam.jmp.LoggableService;
import com.epam.jmp.model.MentorshipProgram;

@LoggableService
public interface MentorshipProgramService extends GenericService<MentorshipProgram> {
	
	List<MentorshipProgram> findByOfficeLocation(String office);
	
	List<MentorshipProgram> findByStartDate(Date date);
	
	MentorshipProgram getFullByUid(String uid);
}
