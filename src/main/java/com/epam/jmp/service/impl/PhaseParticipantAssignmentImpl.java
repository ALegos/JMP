package com.epam.jmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.jmp.dao.PhaseParticipantAssignmentDAO;
import com.epam.jmp.model.PhaseParticipantAssignment;
import com.epam.jmp.service.PhaseParticipantAssignmentService;

@Service
public class PhaseParticipantAssignmentImpl implements PhaseParticipantAssignmentService{

	@Autowired
	private PhaseParticipantAssignmentDAO phaseParticipantAssignmentDAO;
	
	@Override
	public PhaseParticipantAssignment getByUid(long uid) {
		return phaseParticipantAssignmentDAO.getByUid(uid);
	}

	@Override
	public List<PhaseParticipantAssignment> getAll() {
		return phaseParticipantAssignmentDAO.getAll();
	}

	@Override
	public PhaseParticipantAssignment create(PhaseParticipantAssignment t) {
		return phaseParticipantAssignmentDAO.create(t);
	}

	@Override
	public PhaseParticipantAssignment update(PhaseParticipantAssignment t) {
		return phaseParticipantAssignmentDAO.update(t);
	}

	@Override
	public void delete(long uid) {
		phaseParticipantAssignmentDAO.delete(uid);
	}

}
