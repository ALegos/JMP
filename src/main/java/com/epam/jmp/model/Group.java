package com.epam.jmp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.epam.jmp.model.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "group")
public class Group extends AbstractEntity {
	
	private static final long serialVersionUID = -1960682384208218745L;
	
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST })
	@JoinColumn(name = "mentor_uid")
	private Person mentor;
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST })
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
	
}
