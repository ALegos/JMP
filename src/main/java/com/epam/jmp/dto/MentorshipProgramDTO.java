package com.epam.jmp.dto;

import static com.epam.jmp.constants.UtilConstants.DATE_FORMAT_PATTERN;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class MentorshipProgramDTO extends MetaDataSupportedDTO {
	
	private String uid;
	@NotEmpty(message = "Please enter name")
	@Size(min = 5, message = "Name should be more than 5 characters")
	private String name;
	@NotEmpty(message = "Please enter ofiice Location")
	@Size(min = 2, message = "City name should be more than 2 characters")
	private String officeLocation;
	@NotNull(message = "Start date couldn't be empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_PATTERN)
	private Date startDate;
	@NotNull(message = "End date couldn't be empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_PATTERN)
	private Date endDate;
	private GenericCollectonDTO<GroupDTO> groupDTOs;
	private GenericCollectonDTO<PhaseParticipantAssignmentDTO> assigneeDTOs;
}
