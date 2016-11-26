package com.epam.jmp.dto.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.jmp.dto.LectureDTO;
import com.epam.jmp.model.Lecture;
import com.epam.jmp.model.Person;
import com.epam.jmp.service.MentorshipProgramService;
import com.epam.jmp.service.PersonService;

@Component
public class LectureDTOConverter extends DTOConverter<LectureDTO, Lecture> {
	
	private PersonService personService;
	private MentorshipProgramService programService;
	
	@Autowired
	public LectureDTOConverter(ModelMapper mapper, PersonService personService,
			MentorshipProgramService programService) {
		super(mapper);
		this.personService = personService;
		this.programService = programService;
	}
	
	@Override
	public LectureDTO populateDTO(Lecture entity, LectureDTO dto) {
		if (isLoaded(entity.getAttendees())) {
			dto.setAttendeesUids(entity.getAttendees().stream().map(Person::getUid).collect(Collectors.toList()));
		}
		return dto;
	}
	
	@Override
	protected Lecture populateEntity(LectureDTO dto, Lecture entity) {
		if (StringUtils.isNotBlank(dto.getLectorUid())) {
			entity.setLector(personService.getByUid(dto.getLectorUid()));
		}
		if (dto.getAttendeesUids() != null && !dto.getAttendeesUids().isEmpty()) {
			// @formatter:off
			List<Person> attendees = dto.getAttendeesUids()
											.stream()
											.filter(StringUtils::isNotBlank)
											.map(personService::getByUid)
											.filter(l -> l != null)
											.collect(Collectors.toList());
			// @formatter:on
			entity.setAttendees(attendees);
		}
		return entity;
	}
	
}
