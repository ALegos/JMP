package com.epam.jmp.model;

import java.time.Duration;
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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.jmp.model.enums.Status;

@Entity
@Table(name = "lecture")
@XmlRootElement
public class Lecture extends AbstractEntity {
	
	@Column(name = "domain_area")
	private String domainArea;
	@Column(nullable = false)
	private String topic;
	@OneToOne
	@JoinColumn(name = "lector_uid")
	private Person lector;
	@Column(nullable = false)
	private Duration duration;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	@ManyToMany
	@JoinTable(name = "person_lecture", joinColumns = @JoinColumn(name = "lecture_uid", referencedColumnName = "uid"), inverseJoinColumns = @JoinColumn(name = "person_uid", referencedColumnName = "uid"))
	private List<Person> attendees;
	
	public String getDomainArea() {
		return domainArea;
	}
	
	@XmlElement
	public void setDomainArea(String domainArea) {
		this.domainArea = domainArea;
	}
	
	public String getTopic() {
		return topic;
	}
	
	@XmlElement
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public Person getLector() {
		return lector;
	}
	
	@XmlElement
	public void setLector(Person lector) {
		this.lector = lector;
	}
	
	public Duration getDuration() {
		return duration;
	}
	
	@XmlElement
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	
	public Status getStatus() {
		return status;
	}
	
	@XmlElement
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public List<Person> getAttendees() {
		return attendees;
	}
	
	public void setAttendees(List<Person> attendees) {
		this.attendees = attendees;
	}
}
