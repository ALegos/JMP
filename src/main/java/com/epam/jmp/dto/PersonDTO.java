package com.epam.jmp.dto;

import static com.epam.jmp.constants.UtilConstants.DATE_FORMAT_PATTERN;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.epam.jmp.model.enums.Level;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement
public class PersonDTO extends MetaDataSupportedDTO {
	
	private String uid;
	@NotEmpty(message = "Please enter name")
	@Size(min = 5, message = "Name should be between 6 and 15 characters")
	private String name;
	@NotEmpty(message = "Please enter email")
	@Email(message = "Email is not valid")
	private String email;
	private Level level;
	private Boolean excluded = false;
	private String primarySkill;
	private PersonDTO managerDTO = null;
	private GenericCollectonDTO<PersonDTO> subordinateDTOs = null;
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_PATTERN)
	private Date birthDate;
	private PhaseParticipantAssignmentDTO assignmentDTO = null;
	
}
