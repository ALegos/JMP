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
	
	private PersonService personService;
	private MentorshipProgramService programService;
	
	@Autowired
	public GroupDTOConverter(ModelMapper mapper, PersonService personService, MentorshipProgramService programService) {
		super(mapper);
		this.personService = personService;
		this.programService = programService;
	}
	
	@Override
	protected Group populateEntity(GroupDTO dto, Group entity) {
		if (StringUtils.isNotBlank(dto.getMenteeUid())) {
			entity.setMentee(personService.getByUid(dto.getMenteeUid()));
		}
		if (StringUtils.isNotBlank(dto.getMentorUid())) {
			entity.setMentor(personService.getByUid(dto.getMentorUid()));
		}
		if (StringUtils.isNotBlank(dto.getMentorshipProgramUid())) {
			entity.setMentorshipProgram(programService.getByUid(dto.getMentorshipProgramUid()));
		}
		return entity;
	}
	
	@Override
	protected GroupDTO populateDTO(Group entity, GroupDTO dto) {
		return dto;
	}
	
}
