package com.epam.jmp.controller.rest;

import static com.epam.jmp.constants.ControllerConstants.PROGRAM_API_MAPPING;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.epam.jmp.dto.GenericCollectonDTO;
import com.epam.jmp.dto.MentorshipProgramDTO;
import com.epam.jmp.model.MentorshipProgram;
import com.epam.jmp.service.MentorshipProgramService;
import com.epam.jmp.validators.ProgramDTOValidator;

@Controller
@RequestMapping(PROGRAM_API_MAPPING)
public class ProgramController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProgramController.class);
	
	@Autowired
	public MentorshipProgramService programService;
	
	@Autowired
	private ProgramDTOValidator programValidator;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<GenericCollectonDTO<MentorshipProgramDTO>> listAllMentorshipProgram() {
		GenericCollectonDTO<MentorshipProgramDTO> dtos = new GenericCollectonDTO<MentorshipProgramDTO>();
		List<MentorshipProgram> programs = programService.getAll();
		if (programs.isEmpty()) {
			return new ResponseEntity<GenericCollectonDTO<MentorshipProgramDTO>>(HttpStatus.NO_CONTENT);
		} else {
			dtos.setElements(
					programs.stream().map(p -> mapper.map(p, MentorshipProgramDTO.class)).collect(Collectors.toList()));
		}
		return new ResponseEntity<GenericCollectonDTO<MentorshipProgramDTO>>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, params = "office")
	public ResponseEntity<GenericCollectonDTO<MentorshipProgramDTO>> findByOfficeLocation(
			@RequestParam("office") String office) {
		GenericCollectonDTO<MentorshipProgramDTO> dtos = new GenericCollectonDTO<MentorshipProgramDTO>();
		List<MentorshipProgram> programs = programService.findByOfficeLocation(office);
		if (programs.isEmpty()) {
			logger.debug("MentorshipProgram in location " + office + " not found");
			return new ResponseEntity<GenericCollectonDTO<MentorshipProgramDTO>>(HttpStatus.NOT_FOUND);
		} else {
			dtos.setElements(
					programs.stream().map(p -> mapper.map(p, MentorshipProgramDTO.class)).collect(Collectors.toList()));
		}
		return new ResponseEntity<GenericCollectonDTO<MentorshipProgramDTO>>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createMentorshipProgram(@RequestBody MentorshipProgramDTO dto,
			UriComponentsBuilder ucBuilder) {
		String uid = programService.create(mapper.map(dto, MentorshipProgram.class));
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path(PROGRAM_API_MAPPING + "/{id}").buildAndExpand(uid).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.PUT)
	public ResponseEntity<MentorshipProgramDTO> updateMentorshipProgram(@PathVariable("uid") String uid,
			@RequestBody MentorshipProgramDTO program) {
		
		MentorshipProgram currentMentorshipProgram = programService.getByUid(uid);
		
		if (currentMentorshipProgram == null) {
			logger.debug("MentorshipProgram with uid " + uid + " not found");
			return new ResponseEntity<MentorshipProgramDTO>(HttpStatus.NOT_FOUND);
		}
		
		currentMentorshipProgram.setName(program.getName());
		currentMentorshipProgram.setStartDate(program.getStartDate());
		currentMentorshipProgram.setEndDate(program.getEndDate());
		currentMentorshipProgram.setOfficeLocation(program.getOfficeLocation());
		
		currentMentorshipProgram = programService.update(currentMentorshipProgram);
		return new ResponseEntity<MentorshipProgramDTO>(
				mapper.map(currentMentorshipProgram, MentorshipProgramDTO.class), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteMentorshipProgram(@PathVariable("uid") String uid) {
		
		MentorshipProgram user = programService.getByUid(uid);
		if (user == null) {
			logger.debug("Unable to delete. MentorshipProgram with uid " + uid + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		programService.delete(uid);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		binder.addValidators(this.programValidator);
	}
	
}
