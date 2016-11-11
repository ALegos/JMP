package com.epam.jmp.dto.mappings;

import org.modelmapper.PropertyMap;

import com.epam.jmp.dto.PhaseParticipantAssignmentDTO;
import com.epam.jmp.model.PhaseParticipantAssignment;

public class AssignmentMappingHelper {
	
	// mapping to from dto to entity
	public static class AssignmentMap extends PropertyMap<PhaseParticipantAssignmentDTO, PhaseParticipantAssignment> {
		
		@Override
		protected void configure() {
			skip().setPerson(null);
			skip().setMentorshipProgram(null);
		}
	}
	
	// mapping to from entity to dto
	public static class AssignmentDTOMap
			extends PropertyMap<PhaseParticipantAssignment, PhaseParticipantAssignmentDTO> {
		
		@Override
		protected void configure() {
		}
	}
}
