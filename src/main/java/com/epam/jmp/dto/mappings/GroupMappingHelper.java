package com.epam.jmp.dto.mappings;

import org.modelmapper.PropertyMap;

import com.epam.jmp.dto.GroupDTO;
import com.epam.jmp.model.Group;

public class GroupMappingHelper {
	
	// mapping to from dto to entity
	public static class GroupMap extends PropertyMap<GroupDTO, Group> {
		
		@Override
		protected void configure() {
			skip().setMentee(null);
			skip().setMentor(null);
			skip().setMentorshipProgram(null);
		}
	}
	
	// mapping to from entity to dto
	public static class GroupDTOMap extends PropertyMap<Group, GroupDTO> {
		
		@Override
		protected void configure() {
		}
	}
}
