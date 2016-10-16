package com.epam.jmp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.jmp.model.enums.Status;

@Entity
@Table(name = "group")
@XmlRootElement
public class Group extends AbstractEntity {
	
	@OneToOne
	@JoinColumn(name = "mentor_uid")
	private Person mentor;
	@OneToOne
	@JoinColumn(name = "mentee_uid")
	private Person mentee;
	@Column(name = "planned_start")
	private Date plannedStart;
	@Column(name = "planned_end")
	private Date plannedEnd;
	@Column(name = "actual_start")
	private Date actualStart;
	@Column(name = "actual_end")
	private Date actualEnd;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	@ManyToOne
	@JoinColumn(name = "program_uid")
	private MentorshipProgram mentorshipProgram;
	
	public Person getMentor() {
		return mentor;
	}
	
	@XmlElement
	public void setMentor(Person mentor) {
		this.mentor = mentor;
	}
	
	public Person getMentee() {
		return mentee;
	}
	
	@XmlElement
	public void setMentee(Person mentee) {
		this.mentee = mentee;
	}
	
	public Date getPlannedStart() {
		return plannedStart;
	}
	
	@XmlElement
	public void setPlannedStart(Date plannedStart) {
		this.plannedStart = plannedStart;
	}
	
	public Date getPlannedEnd() {
		return plannedEnd;
	}
	
	@XmlElement
	public void setPlannedEnd(Date plannedEnd) {
		this.plannedEnd = plannedEnd;
	}
	
	public Date getActualStart() {
		return actualStart;
	}
	
	@XmlElement
	public void setActualStart(Date actualStart) {
		this.actualStart = actualStart;
	}
	
	public Date getActualEnd() {
		return actualEnd;
	}
	
	@XmlElement
	public void setActualEnd(Date actualEnd) {
		this.actualEnd = actualEnd;
	}
	
	public Status getStatus() {
		return status;
	}
	
	@XmlElement
	public void setStatus(Status status) {
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
