package com.epam.jmp.service.impl;

import java.util.Date;
import java.util.List;

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
	
	@Override
	public List<MentorshipProgram> findByOfficeLocation(String office) {
		return genericDAO.findByOfficeLocation(office);
	}
	
	@Override
	public List<MentorshipProgram> findByStartDate(Date date) {
		return genericDAO.findByStartDate(date);
	}
	
	@Override
	public MentorshipProgram getFullByUid(String uid) {
		return genericDAO.getFullByUid(uid);
	}
	
}
