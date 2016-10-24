package com.epam.jmp.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.epam.jmp.model.enums.ParticipantAssignmentStatus;
import com.epam.jmp.model.enums.Role;

import lombok.Data;

@Data
public class PhaseParticipantAssignmentDTO {
	
	private String uid;
	@NotEmpty(message = "Should contain person UUID")
	@Size(min = 32, max = 32, message = "Acceptable length is 32 characters")
	private String personUid;
	@NotEmpty(message = "Should contain mentorship program UUID")
	@Size(min = 32, max = 32, message = "Acceptable length is 32 characters")
	private String mentorshipProgramUid;
	@NotEmpty(message = "Please specify person role")
	private Role role;
	@NotEmpty(message = "Please specify assignment status")
	private ParticipantAssignmentStatus status;
}
