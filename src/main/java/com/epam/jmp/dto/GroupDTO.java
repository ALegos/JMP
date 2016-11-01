package com.epam.jmp.dto;

import static com.epam.jmp.constants.UtilConstants.DATETIME_FORMAT_PATTERN;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.epam.jmp.model.MentorshipProgram;
import com.epam.jmp.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@XmlRootElement
public class GroupDTO {
	
	private String uid;
	@NotEmpty(message = "Should contain mentor UUID")
	@Size(min = 32, max = 32, message = "Acceptable length is 32 characters")
	private String mentorUid;
	@NotEmpty(message = "Should contain mentee UUID")
	@Size(min = 32, max = 32, message = "Acceptable length is 32 characters")
	private String menteeUid;
	@NotNull(message = "Start date couldn't be empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT_PATTERN)
	private Date plannedStart;
	@NotNull(message = "End date couldn't be empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT_PATTERN)
	private Date plannedEnd;
	private Date actualStart;
	private Date actualEnd;
	@NotNull(message = "Please specify status")
	private Status status;
	private MentorshipProgram mentorshipProgram;
	
}
