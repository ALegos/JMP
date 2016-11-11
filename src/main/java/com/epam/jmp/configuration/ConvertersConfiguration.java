package com.epam.jmp.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.jmp.dto.mappings.AssignmentMappingHelper;
import com.epam.jmp.dto.mappings.GroupMappingHelper;
import com.epam.jmp.dto.mappings.LectureMappingHelper;
import com.epam.jmp.dto.mappings.PersonMappingHelper;
import com.epam.jmp.dto.mappings.ProgramMappingHelper;

@Configuration
public class ConvertersConfiguration {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(new PersonMappingHelper.PersonMap());
		mapper.addMappings(new PersonMappingHelper.PersonDTOMap());
		mapper.addMappings(new ProgramMappingHelper.MentorshipProgramMap());
		mapper.addMappings(new ProgramMappingHelper.MentorshipProgramDTOMap());
		mapper.addMappings(new AssignmentMappingHelper.AssignmentMap());
		mapper.addMappings(new AssignmentMappingHelper.AssignmentDTOMap());
		mapper.addMappings(new GroupMappingHelper.GroupMap());
		mapper.addMappings(new GroupMappingHelper.GroupDTOMap());
		mapper.addMappings(new LectureMappingHelper.LectureMap());
		mapper.addMappings(new LectureMappingHelper.LectureDTOMap());
		return mapper;
	}
}
