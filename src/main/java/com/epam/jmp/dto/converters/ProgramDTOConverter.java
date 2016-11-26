package com.epam.jmp.dto.converters;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.jmp.dto.GenericCollectonDTO;
import com.epam.jmp.dto.GroupDTO;
import com.epam.jmp.dto.MentorshipProgramDTO;
import com.epam.jmp.dto.PhaseParticipantAssignmentDTO;
import com.epam.jmp.model.MentorshipProgram;

@Component
public class ProgramDTOConverter extends DTOConverter<MentorshipProgramDTO, MentorshipProgram> {
	
	private GroupDTOConverter groupConverter;
	private AssignmentDTOConverter assignmentConverter;
	
	@Autowired
	public ProgramDTOConverter(ModelMapper mapper, GroupDTOConverter groupConverter,
			AssignmentDTOConverter assignmentConverter) {
		super(mapper);
		this.groupConverter = groupConverter;
		this.assignmentConverter = assignmentConverter;
	}
	
	@Override
	protected MentorshipProgram populateEntity(MentorshipProgramDTO dto, MentorshipProgram entity) {
		return entity;
	}
	
	@Override
	protected MentorshipProgramDTO populateDTO(MentorshipProgram entity, MentorshipProgramDTO dto) {
		if (isLoaded(entity.getGroups())) {
			GenericCollectonDTO<GroupDTO> groupDTOs = new GenericCollectonDTO<GroupDTO>();
			if (!entity.getGroups().isEmpty()) {
				groupDTOs.setElements(
						entity.getGroups().stream().map(groupConverter::toDTO).collect(Collectors.toList()));
				
			}
			dto.setGroupDTOs(groupDTOs);
		}
		if (isLoaded(entity.getAssignees())) {
			GenericCollectonDTO<PhaseParticipantAssignmentDTO> assignmentDTOs = new GenericCollectonDTO<PhaseParticipantAssignmentDTO>();
			if (!entity.getGroups().isEmpty()) {
				assignmentDTOs.setElements(
						entity.getAssignees().stream().map(assignmentConverter::toDTO).collect(Collectors.toList()));
				
			}
			dto.setAssigneeDTOs(assignmentDTOs);
		}
		return dto;
	}
	
}
