package com.epam.jmp.model;

import java.time.Duration;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.epam.jmp.model.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lectures")
public class Lecture extends AbstractEntity {
	
	private static final long serialVersionUID = 7804610138239802948L;
	
	@Column(name = "domain_area")
	private String domainArea;
	@Column(nullable = false)
	private String topic;
	@OneToOne
	@JoinColumn(name = "lector_uid")
	private Person lector;
	@Column(nullable = false)
	private Duration duration;
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
	@ManyToMany
	@JoinTable(name = "person_lecture", joinColumns = @JoinColumn(name = "lecture_uid", referencedColumnName = "uid"), inverseJoinColumns = @JoinColumn(name = "person_uid", referencedColumnName = "uid"))
	private List<Person> attendees;
	
}
