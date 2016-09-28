package com.epam.jmp.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.epam.jmp.model.MentorshipProgram;

@Repository
public class MentorshipProgramDAO extends GenericDAO<MentorshipProgram> {
	
	public List<MentorshipProgram> findByOfficeLocation(String office) {
		return this.getAll().stream().filter(p -> office.equalsIgnoreCase(p.getOfficeLocation()))
				.collect(Collectors.toList());
	}
	
}
