package com.epam.jmp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.epam.jmp.model.enums.ParticipantAssignmentStatus;
import com.epam.jmp.model.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "phase_participant_assignment")
public class PhaseParticipantAssignment extends AbstractEntity {
	
	private static final long serialVersionUID = 4696377008477642056L;
	
	@OneToOne
	@JoinColumn(name = "person_uid")
	private Person person;
	@OneToOne
	@JoinColumn(name = "program_uid")
	private MentorshipProgram mentorshipProgram;
	@Column
	@Enumerated(EnumType.STRING)
	private Role role;
	@Column
	@Enumerated(EnumType.STRING)
	private ParticipantAssignmentStatus status;
	
}
