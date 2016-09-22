package com.epam.jmp.model;

import java.util.Date;

import com.epam.jmp.model.enums.Status;

public class Group extends AbstractEntity {

	private Person mentor;
	private Person mentee;
	private Date plannedStart;
	private Date plannedEnd;
	private Date actualStart;
	private Date actualEnd;
	private Status status;
	public Person getMentor() {
		return mentor;
	}
	public void setMentor(Person mentor) {
		this.mentor = mentor;
	}
	public Person getMentee() {
		return mentee;
	}
	public void setMentee(Person mentee) {
		this.mentee = mentee;
	}
	public Date getPlannedStart() {
		return plannedStart;
	}
	public void setPlannedStart(Date plannedStart) {
		this.plannedStart = plannedStart;
	}
	public Date getPlannedEnd() {
		return plannedEnd;
	}
	public void setPlannedEnd(Date plannedEnd) {
		this.plannedEnd = plannedEnd;
	}
	public Date getActualStart() {
		return actualStart;
	}
	public void setActualStart(Date actualStart) {
		this.actualStart = actualStart;
	}
	public Date getActualEnd() {
		return actualEnd;
	}
	public void setActualEnd(Date actualEnd) {
		this.actualEnd = actualEnd;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
