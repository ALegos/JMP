package com.epam.jmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.jmp.dao.MentorshipProgramDAO;
import com.epam.jmp.model.MentorshipProgram;
import com.epam.jmp.service.MentorshipProgramService;

@Service
public class MentorshipProgramServiceImpl extends GenericServiceImpl<MentorshipProgram, MentorshipProgramDAO>
		implements MentorshipProgramService {

	@Autowired
	public MentorshipProgramServiceImpl(MentorshipProgramDAO managerDAO) {
		super(managerDAO);
	}

}
