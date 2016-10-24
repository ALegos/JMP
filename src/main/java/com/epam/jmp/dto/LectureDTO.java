package com.epam.jmp.dto;

import static com.epam.jmp.constants.UtilConstants.DATETIME_FORMAT_PATTERN;
import static com.epam.jmp.constants.UtilConstants.DURATION_FORMAT_PATTERN;

import java.time.Duration;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.epam.jmp.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class LectureDTO {
	
	private String uid;
	@NotEmpty(message = "Please specify domain area")
	private String domainArea;
	@NotEmpty(message = "Please specify lecture topic")
	private String topic;
	@NotEmpty(message = "Should contain lector UUID")
	@Size(min = 32, max = 32, message = "Acceptable length is 32 characters")
	private String lectorUid;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DURATION_FORMAT_PATTERN)
	private Duration duration;
	@NotEmpty(message = "Start date couldn't be empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT_PATTERN)
	private Date plannedStart;
	@NotEmpty(message = "End date couldn't be empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT_PATTERN)
	private Date plannedEnd;
	private Date actualStart;
	private Date actualEnd;
	private Status status;
}
