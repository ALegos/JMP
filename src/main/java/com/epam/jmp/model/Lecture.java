package com.epam.jmp.model;

import java.time.Duration;

import com.epam.jmp.model.enums.Status;

public class Lecture extends AbstractEntity{
	private String domainArea;
	private String topic;
	private Person lector;
	private Duration duration;
	private Status status;
	
	public String getDomainArea() {
		return domainArea;
	}
	public void setDomainArea(String domainArea) {
		this.domainArea = domainArea;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Person getLector() {
		return lector;
	}
	public void setLector(Person lector) {
		this.lector = lector;
	}
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
