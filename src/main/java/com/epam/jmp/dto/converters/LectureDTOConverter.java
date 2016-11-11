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
	
	private ModelMapper mapper;
	private PersonService personService;
	private MentorshipProgramService programService;
	
	@Autowired
	public LectureDTOConverter(ModelMapper mapper, PersonService personService,
			MentorshipProgramService programService) {
		this.mapper = mapper;
		this.personService = personService;
		this.programService = programService;
	}
	
	@Override
	public Lecture toEntity(LectureDTO dto) {
		Lecture result = this.mapper.map(dto, Lecture.class);
		if (StringUtils.isNotBlank(dto.getLectorUid())) {
			result.setLector(personService.getByUid(dto.getLectorUid()));
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
			result.setAttendees(attendees);
		}
		return result;
	}
	
	@Override
	public LectureDTO toDTO(Lecture entity) {
		LectureDTO result = this.mapper.map(entity, LectureDTO.class);
		if (isLoaded(entity.getAttendees())) {
			result.setAttendeesUids(entity.getAttendees().stream().map(Person::getUid).collect(Collectors.toList()));
		}
		return result;
	}
	
}
