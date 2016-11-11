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
		this.mapper = mapper;
		this.personService = personService;
		this.assignmentConverter = assignmentConverter;
	}
	
	@Override
	public Person toEntity(PersonDTO dto) {
		Person result = this.mapper.map(dto, Person.class);
		if (dto.getManagerDTO() != null && StringUtils.isNotBlank(dto.getManagerDTO().getUid())) {
			result.setManager(this.personService.getByUid(dto.getManagerDTO().getUid()));
		}
		if (dto.getAssignmentDTO() != null) {
			if (StringUtils.isNotBlank(dto.getAssignmentDTO().getMentorshipProgramUid())) {
				dto.getAssignmentDTO().setPersonUid(dto.getUid());
				result.setAssignment(assignmentConverter.toEntity(dto.getAssignmentDTO()));
				if (StringUtils.isBlank(dto.getUid())) {
					result.getAssignment().setPerson(result);
				}
			} else {
				result.setAssignment(null);
			}
		}
		
		return result;
	}
	
	@Override
	public PersonDTO toDTO(Person entity) {
		PersonDTO result = this.mapper.map(entity, PersonDTO.class);
		if (isLoaded(entity.getManager())) {
			result.setManagerDTO(this.mapper.map(entity.getManager(), PersonDTO.class));
		}
		if (isLoaded(entity.getAssignment())) {
			result.setAssignmentDTO(assignmentConverter.toDTO(entity.getAssignment()));
		}
		return result;
	}
	
}
