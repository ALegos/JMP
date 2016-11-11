package com.epam.jmp.dto.mappings;

import org.modelmapper.PropertyMap;

import com.epam.jmp.dto.MentorshipProgramDTO;
import com.epam.jmp.model.MentorshipProgram;

public class ProgramMappingHelper {
	
	// mapping to from dto to entity
	public static class MentorshipProgramMap extends PropertyMap<MentorshipProgramDTO, MentorshipProgram> {
		
		@Override
		protected void configure() {
			skip().setAssignees(null);
			skip().setGroups(null);
		}
	}
	
	// mapping to from entity to dto
	public static class MentorshipProgramDTOMap extends PropertyMap<MentorshipProgram, MentorshipProgramDTO> {
		
		@Override
		protected void configure() {
			skip().setAssigneeDTOs(null);
			skip().setGroupDTOs(null);
		}
	}
}
