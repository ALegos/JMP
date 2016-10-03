package com.epam.jmp.model;

import static com.epam.jmp.constants.UtilConstants.DATE_FORMAT_PATTERN;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

@XmlRootElement
public class MentorshipProgram extends AbstractEntity {
	
	private String name;
	private String officeLocation;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_PATTERN)
	private Date startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_PATTERN)
	private Date endDate;
	private List<Group> groups;
	
	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOfficeLocation() {
		return officeLocation;
	}
	
	@XmlElement
	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	@XmlElement
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	@XmlElement
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public List<Group> getGroups() {
		return groups;
	}
	
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
}
