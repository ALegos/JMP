package com.epam.jmp.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.epam.jmp.model.PhaseParticipantAssignment;

@Repository
public class PhaseParticipantAssignmentDAO extends GenericDAO<PhaseParticipantAssignment> {
	
	public PhaseParticipantAssignment getByPersonUid(String uid) {
		final StringBuffer queryString = new StringBuffer("SELECT a from ");
		queryString.append(getType().getSimpleName()).append(" a ");
		queryString.append("WHERE a.person.uid = :uid");
		TypedQuery<PhaseParticipantAssignment> query = getEntityManager().createQuery(queryString.toString(),
				PhaseParticipantAssignment.class);
		query.setParameter("uid", uid);
		return query.getSingleResult();
	}
	
}
