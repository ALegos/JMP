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
	
	private ModelMapper mapper;
	private GroupDTOConverter groupConverter;
	private AssignmentDTOConverter assignmentConverter;
	
	@Autowired
	public ProgramDTOConverter(ModelMapper mapper, GroupDTOConverter groupConverter,
			AssignmentDTOConverter assignmentConverter) {
		this.mapper = mapper;
		this.groupConverter = groupConverter;
		this.assignmentConverter = assignmentConverter;
	}
	
	@Override
	public MentorshipProgram toEntity(MentorshipProgramDTO dto) {
		MentorshipProgram result = this.mapper.map(dto, MentorshipProgram.class);
		return result;
	}
	
	@Override
	public MentorshipProgramDTO toDTO(MentorshipProgram entity) {
		MentorshipProgramDTO result = this.mapper.map(entity, MentorshipProgramDTO.class);
		if (isLoaded(entity.getGroups())) {
			GenericCollectonDTO<GroupDTO> groupDTOs = new GenericCollectonDTO<GroupDTO>();
			if (!entity.getGroups().isEmpty()) {
				groupDTOs.setElements(
						entity.getGroups().stream().map(groupConverter::toDTO).collect(Collectors.toList()));
				
			}
			result.setGroupDTOs(groupDTOs);
		}
		if (isLoaded(entity.getAssignees())) {
			GenericCollectonDTO<PhaseParticipantAssignmentDTO> assignmentDTOs = new GenericCollectonDTO<PhaseParticipantAssignmentDTO>();
			if (!entity.getGroups().isEmpty()) {
				assignmentDTOs.setElements(
						entity.getAssignees().stream().map(assignmentConverter::toDTO).collect(Collectors.toList()));
				
			}
			result.setAssigneeDTOs(assignmentDTOs);
		}
		return result;
	}
	
}
