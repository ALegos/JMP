package com.epam.jmp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Embeddable
@XmlRootElement
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
	
	@XmlElement
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Date getModificationDate() {
		return modificationDate;
	}
	
	@XmlElement
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	
	public String getCreationInfo() {
		return creationInfo;
	}
	
	@XmlElement
	public void setCreationInfo(String creationInfo) {
		this.creationInfo = creationInfo;
	}
	
	public String getModificationInfo() {
		return modificationInfo;
	}
	
	@XmlElement
	public void setModificationInfo(String modificationInfo) {
		this.modificationInfo = modificationInfo;
	}
}
