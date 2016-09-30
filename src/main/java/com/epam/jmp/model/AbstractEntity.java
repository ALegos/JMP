package com.epam.jmp.model;

import java.util.Date;
import java.util.UUID;

public abstract class AbstractEntity {

	private String uid;
	private Date dateCreated;
	private Date lastmodified;
	private String createdByUser;
	private String modifiedByUser;

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

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastmodified() {
		return lastmodified;
	}

	public void setLastmodified(Date lastmodified) {
		this.lastmodified = lastmodified;
	}

	public String getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}

	public String getModifiedByUser() {
		return modifiedByUser;
	}

	public void setModifiedByUser(String modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}

}
