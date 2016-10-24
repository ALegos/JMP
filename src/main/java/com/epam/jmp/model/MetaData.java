package com.epam.jmp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MetaData {
	
	@Column(name = "creation_date", updatable = false)
	private Date creationDate;
	@Column(name = "modification_date")
	private Date modificationDate;
	@Column(name = "creation_info", updatable = false)
	private String creationInfo;
	@Column(name = "modification_info")
	private String modificationInfo;
	
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
}
