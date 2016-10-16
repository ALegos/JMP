package com.epam.jmp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.jmp.model.enums.ParticipantAssignmentStatus;
import com.epam.jmp.model.enums.Role;

@Entity
@XmlRootElement
@Table(name = "phase_participant_assignment")
public class PhaseParticipantAssignment extends AbstractEntity {
	
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
	
	public Person getPerson() {
		return person;
	}
	
	@XmlElement
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Role getRole() {
		return role;
	}
	
	@XmlElement
	public void setRole(Role role) {
		this.role = role;
	}
	
	public ParticipantAssignmentStatus getStatus() {
		return status;
	}
	
	@XmlElement
	public void setStatus(ParticipantAssignmentStatus status) {
		this.status = status;
	}
	
	public MentorshipProgram getMentorshipProgram() {
		return mentorshipProgram;
	}
	
	@XmlElement
	public void setMentorshipProgram(MentorshipProgram mentorshipProgram) {
		this.mentorshipProgram = mentorshipProgram;
	}
}
