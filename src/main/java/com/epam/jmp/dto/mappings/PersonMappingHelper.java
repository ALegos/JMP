package com.epam.jmp.dto.mappings;

import org.modelmapper.PropertyMap;

import com.epam.jmp.dto.PersonDTO;
import com.epam.jmp.model.Person;

public class PersonMappingHelper {
	
	// mapping to from dto to entity
	public static class PersonMap extends PropertyMap<PersonDTO, Person> {
		
		@Override
		protected void configure() {
			map().setIsManager(source.getIsManager());
			skip().setManager(null);
			skip().setAssignment(null);
			skip().setSubordinates(null);
		}
	}
	
	// mapping to from entity to dto
	public static class PersonDTOMap extends PropertyMap<Person, PersonDTO> {
		
		@Override
		protected void configure() {
			map().setIsManager(source.getIsManager());
			skip().setManagerDTO(null);
			skip().setAssignmentDTO(null);
			skip().setSubordinateDTOs(null);
		}
	}
}
