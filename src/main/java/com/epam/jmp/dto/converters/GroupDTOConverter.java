package com.epam.jmp.dto.converters;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.jmp.dto.GroupDTO;
import com.epam.jmp.model.Group;
import com.epam.jmp.service.MentorshipProgramService;
import com.epam.jmp.service.PersonService;

@Component
public class GroupDTOConverter extends DTOConverter<GroupDTO, Group> {
	
	private ModelMapper mapper;
	private PersonService personService;
	private MentorshipProgramService programService;
	
	@Autowired
	public GroupDTOConverter(ModelMapper mapper, PersonService personService, MentorshipProgramService programService) {
		this.mapper = mapper;
		this.personService = personService;
		this.programService = programService;
	}
	
	@Override
	public Group toEntity(GroupDTO dto) {
		Group result = this.mapper.map(dto, Group.class);
		if (StringUtils.isNotBlank(dto.getMenteeUid())) {
			result.setMentee(personService.getByUid(dto.getMenteeUid()));
		}
		if (StringUtils.isNotBlank(dto.getMentorUid())) {
			result.setMentor(personService.getByUid(dto.getMentorUid()));
		}
		if (StringUtils.isNotBlank(dto.getMentorshipProgramUid())) {
			result.setMentorshipProgram(programService.getByUid(dto.getMentorshipProgramUid()));
		}
		return result;
	}
	
	@Override
	public GroupDTO toDTO(Group entity) {
		GroupDTO result = this.mapper.map(entity, GroupDTO.class);
		return result;
	}
	
}
