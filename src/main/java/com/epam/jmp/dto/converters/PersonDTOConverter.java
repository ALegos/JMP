package com.epam.jmp.dto.converters;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.jmp.dto.PersonDTO;
import com.epam.jmp.model.Person;
import com.epam.jmp.service.PersonService;

@Component
public class PersonDTOConverter extends DTOConverter<PersonDTO, Person> {
	
	private ModelMapper mapper;
	private PersonService personService;
	private AssignmentDTOConverter assignmentConverter;
	
	@Autowired
	public PersonDTOConverter(ModelMapper mapper, PersonService personService,
			AssignmentDTOConverter assignmentConverter) {
		super(mapper);
		this.personService = personService;
		this.assignmentConverter = assignmentConverter;
	}
	
	@Override
	protected Person populateEntity(PersonDTO dto, Person entity) {
		if (dto.getManagerDTO() != null && StringUtils.isNotBlank(dto.getManagerDTO().getUid())) {
			entity.setManager(this.personService.getByUid(dto.getManagerDTO().getUid()));
		}
		if (dto.getAssignmentDTO() != null) {
			if (StringUtils.isNotBlank(dto.getAssignmentDTO().getMentorshipProgramUid())) {
				dto.getAssignmentDTO().setPersonUid(dto.getUid());
				entity.setAssignment(assignmentConverter.toEntity(dto.getAssignmentDTO()));
				if (StringUtils.isBlank(dto.getUid())) {
					entity.getAssignment().setPerson(entity);
				}
			} else {
				entity.setAssignment(null);
			}
		}
		
		return entity;
	}
	
	@Override
	protected PersonDTO populateDTO(Person entity, PersonDTO dto) {
		if (isLoaded(entity.getManager())) {
			dto.setManagerDTO(this.toDTO(entity.getManager()));
		}
		if (isLoaded(entity.getAssignment())) {
			dto.setAssignmentDTO(assignmentConverter.toDTO(entity.getAssignment()));
		}
		return dto;
	}
	
}
