package com.epam.jmp.controller;

import static com.epam.jmp.controller.ControllerConstants.PROGRAM_API_MAPPING;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.epam.jmp.model.MentorshipProgram;
import com.epam.jmp.service.MentorshipProgramService;

@Controller
@RequestMapping(PROGRAM_API_MAPPING)
public class ProgramController {
	
	@Autowired
	public MentorshipProgramService programService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MentorshipProgram>> listAllMentorshipProgram() {
		List<MentorshipProgram> programs = programService.getAll();
		if (programs.isEmpty()) {
			return new ResponseEntity<List<MentorshipProgram>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MentorshipProgram>>(programs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, params = "office")
	public ResponseEntity<List<MentorshipProgram>> findByOfficeLocation(@RequestParam("office") String office) {
		// TODO remove sysout
		System.out.println("Fetching MentorshipPrograms by office " + office);
		List<MentorshipProgram> programs = programService.findByOfficeLocation(office);
		if (programs.isEmpty()) {
			// TODO remove sysout
			System.out.println("MentorshipProgram in location " + office + " not found");
			return new ResponseEntity<List<MentorshipProgram>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<MentorshipProgram>>(programs, HttpStatus.OK);
	}
	
	// TODO User validation
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createMentorshipProgram(@RequestBody MentorshipProgram program,
			UriComponentsBuilder ucBuilder) {
		// TODO remove sysout
		System.out.println("Creating MentorshipProgram " + program.getName());
		programService.create(program);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/program/{id}").buildAndExpand(program.getUid()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.PUT)
	public ResponseEntity<MentorshipProgram> updateMentorshipProgram(@PathVariable("uid") String uid,
			@RequestBody MentorshipProgram program) {
		// TODO remove sysout
		System.out.println("Updating MentorshipProgram " + uid);
		MentorshipProgram currentMentorshipProgram = programService.getByUid(uid);
		
		if (currentMentorshipProgram == null) {
			// TODO remove sysout
			System.out.println("MentorshipProgram with uid " + uid + " not found");
			return new ResponseEntity<MentorshipProgram>(HttpStatus.NOT_FOUND);
		}
		
		currentMentorshipProgram.setName(program.getName());
		currentMentorshipProgram.setStartDate(program.getStartDate());
		currentMentorshipProgram.setEndDate(program.getEndDate());
		currentMentorshipProgram.setOfficeLocation(program.getOfficeLocation());
		currentMentorshipProgram.setGroups(program.getGroups());
		
		// TODO update email validation
		
		programService.update(currentMentorshipProgram);
		return new ResponseEntity<MentorshipProgram>(currentMentorshipProgram, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.DELETE)
	public ResponseEntity<MentorshipProgram> deleteMentorshipProgram(@PathVariable("uid") String uid) {
		// TODO remove sysout
		System.out.println("Fetching & Deleting MentorshipProgram with uid " + uid);
		
		MentorshipProgram user = programService.getByUid(uid);
		if (user == null) {
			// TODO remove sysout
			System.out.println("Unable to delete. MentorshipProgram with uid " + uid + " not found");
			return new ResponseEntity<MentorshipProgram>(HttpStatus.NOT_FOUND);
		}
		
		programService.delete(uid);
		return new ResponseEntity<MentorshipProgram>(HttpStatus.NO_CONTENT);
	}
	
}
