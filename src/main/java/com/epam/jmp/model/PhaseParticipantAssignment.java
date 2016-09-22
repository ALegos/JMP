package com.epam.jmp.model;

import com.epam.jmp.model.enums.ParticipantAssignmentStatus;
import com.epam.jmp.model.enums.Role;

public class PhaseParticipantAssignment extends AbstractEntity{

	private Person person;
	private Role role;
	private ParticipantAssignmentStatus status;

	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public ParticipantAssignmentStatus getStatus() {
		return status;
	}
	public void setStatus(ParticipantAssignmentStatus status) {
		this.status = status;
	}
}
