package com.epam.jmp.dto.mappings;

import org.modelmapper.PropertyMap;

import com.epam.jmp.dto.LectureDTO;
import com.epam.jmp.model.Lecture;

public class LectureMappingHelper {
	
	// mapping to from dto to entity
	public static class LectureMap extends PropertyMap<LectureDTO, Lecture> {
		
		@Override
		protected void configure() {
			skip().setLector(null);
			skip().setAttendees(null);
		}
	}
	
	// mapping to from entity to dto
	public static class LectureDTOMap extends PropertyMap<Lecture, LectureDTO> {
		
		@Override
		protected void configure() {
			skip().setAttendeesUids(null);
		}
	}
}
