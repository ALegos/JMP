package com.epam.jmp.model;

import java.util.Date;
import java.util.UUID;

public abstract class AbstractEntity {
	
	private String uid;
	private Date creationDate;
	private Date modificationDate;
	private String creationInfo;
	private String modificationInfo;
	
	public AbstractEntity() {
	}
	
	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Date getModificationDate() {
		return modificationDate;
	}
	
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	
	public String getCreationInfo() {
		return creationInfo;
	}
	
	public void setCreationInfo(String creationInfo) {
		this.creationInfo = creationInfo;
	}
	
	public String getModificationInfo() {
		return modificationInfo;
	}
	
	public void setModificationInfo(String modificationInfo) {
		this.modificationInfo = modificationInfo;
	}
	
	public String generateUid() {
		this.setUid(UUID.randomUUID().toString());
		return this.getUid();
	}
	
}
