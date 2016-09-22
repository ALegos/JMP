package com.epam.jmp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.jmp.model.PhaseParticipantAssignment;
import com.epam.jmp.persistance.PersistanceUnit;

@Repository
public class PhaseParticipantAssignmentDAO extends GenericDAO<PhaseParticipantAssignment> {

	@Autowired
	public PhaseParticipantAssignmentDAO(PersistanceUnit storage) {
		super(storage, PhaseParticipantAssignment.class);
	}

}
