package com.epam.jmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.jmp.dao.PhaseParticipantAssignmentDAO;
import com.epam.jmp.model.PhaseParticipantAssignment;
import com.epam.jmp.service.PhaseParticipantAssignmentService;

@Service
public class PhaseParticipantAssignmentImpl
		extends GenericServiceImpl<PhaseParticipantAssignment, PhaseParticipantAssignmentDAO>
		implements PhaseParticipantAssignmentService {

	@Autowired
	public PhaseParticipantAssignmentImpl(PhaseParticipantAssignmentDAO genericDAO) {
		super(genericDAO);
	}

}
