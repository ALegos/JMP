package com.epam.jmp.dto;

import static com.epam.jmp.constants.UtilConstants.DATE_FORMAT_PATTERN;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;

import com.epam.jmp.model.Group;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement
public class MentorshipProgramDTO extends MetaDataSupportedDTO {
	
	private String uid;
	@NotEmpty(message = "Please enter name")
	@Size(min = 5, message = "Name should be more than 5 characters")
	private String name;
	@NotEmpty(message = "Please enter ofiice Location")
	@Size(min = 2, message = "City name should be more than 2 characters")
	private String officeLocation;
	@NotEmpty(message = "Start date couldn't be empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_PATTERN)
	private Date startDate;
	@NotEmpty(message = "End date couldn't be empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_PATTERN)
	private Date endDate;
	@XmlTransient
	@JsonIgnore
	private List<Group> groups;
}
