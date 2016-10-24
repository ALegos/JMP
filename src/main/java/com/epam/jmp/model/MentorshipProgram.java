package com.epam.jmp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mentorship_program")
public class MentorshipProgram extends MetaDataSupportedAbstractEntity {
	
	private static final long serialVersionUID = -4522396180724209788L;
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, name = "office_location")
	private String officeLocation;
	@Column(nullable = false, name = "start_date")
	private Date startDate;
	@Column(nullable = false, name = "end_date")
	private Date endDate;
	@OneToMany(mappedBy = "mentorshipProgram")
	private List<Group> groups;
	
}
