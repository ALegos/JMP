package com.epam.jmp.model;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class AbstractEntity {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String uid;
	
	public AbstractEntity() {
	}
	
	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String generateUid() {
		this.setUid(UUID.randomUUID().toString());
		return this.getUid();
	}
	
}
