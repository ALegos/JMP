package com.epam.jmp.dto.converters;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.jmp.dto.PhaseParticipantAssignmentDTO;
import com.epam.jmp.model.PhaseParticipantAssignment;
import com.epam.jmp.service.MentorshipProgramService;
import com.epam.jmp.service.PersonService;
import com.epam.jmp.service.PhaseParticipantAssignmentService;

@Component
public class AssignmentDTOConverter extends DTOConverter<PhaseParticipantAssignmentDTO, PhaseParticipantAssignment> {
	
	private ModelMapper mapper;
	private PersonService personService;
	private MentorshipProgramService programService;
	private PhaseParticipantAssignmentService assignmentService;
	
	@Autowired
	public AssignmentDTOConverter(ModelMapper mapper, PersonService personService,
			PhaseParticipantAssignmentService assignmentService, MentorshipProgramService programService) {
		this.mapper = mapper;
		this.personService = personService;
		this.assignmentService = assignmentService;
		this.programService = programService;
	}
	
	@Override
	public PhaseParticipantAssignment toEntity(PhaseParticipantAssignmentDTO dto) {
		PhaseParticipantAssignment assignment = null;
		if (StringUtils.isBlank(dto.getUid())) {
			dto.setUid(null);
		}
		if (StringUtils.isNotBlank(dto.getUid())) {
			assignment = assignmentService.getByUid(dto.getUid());
		} else {
			assignment = new PhaseParticipantAssignment();
		}
		mapper.map(dto, assignment);
		if (StringUtils.isNotBlank(dto.getPersonUid())) {
			assignment.setPerson(personService.getByUid(dto.getPersonUid()));
		}
		if (StringUtils.isNotBlank(dto.getMentorshipProgramUid())) {
			assignment.setMentorshipProgram(programService.getByUid(dto.getMentorshipProgramUid()));
		}
		
		return assignment;
	}
	
	@Override
	public PhaseParticipantAssignmentDTO toDTO(PhaseParticipantAssignment entity) {
		PhaseParticipantAssignmentDTO result = this.mapper.map(entity, PhaseParticipantAssignmentDTO.class);
		return result;
	}
	
}
