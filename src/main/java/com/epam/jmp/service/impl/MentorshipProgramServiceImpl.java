package com.epam.jmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.jmp.dao.MentorshipProgramDAO;
import com.epam.jmp.model.MentorshipProgram;
import com.epam.jmp.service.MentorshipProgramService;

@Service
public class MentorshipProgramServiceImpl implements MentorshipProgramService{

	@Autowired
	private MentorshipProgramDAO mentorshipProgramDAO;
	
	@Override
	public MentorshipProgram getByUid(long uid) {
		return mentorshipProgramDAO.getByUid(uid);
	}

	@Override
	public List<MentorshipProgram> getAll() {
		return mentorshipProgramDAO.getAll();
	}

	@Override
	public MentorshipProgram create(MentorshipProgram t) {
		return mentorshipProgramDAO.create(t);
	}

	@Override
	public MentorshipProgram update(MentorshipProgram t) {
		return mentorshipProgramDAO.update(t);
	}

	@Override
	public void delete(long uid) {
		mentorshipProgramDAO.delete(uid);
	}

}
