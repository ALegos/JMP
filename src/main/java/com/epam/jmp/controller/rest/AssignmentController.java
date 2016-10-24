package com.epam.jmp.controller.rest;

import static com.epam.jmp.constants.ControllerConstants.ASSIGNMENTS_API;
import static com.epam.jmp.constants.ControllerConstants.ASSIGNMENT_API_MAPPING;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.epam.jmp.dto.GenericCollectonDTO;
import com.epam.jmp.dto.PhaseParticipantAssignmentDTO;
import com.epam.jmp.model.PhaseParticipantAssignment;
import com.epam.jmp.service.PhaseParticipantAssignmentService;
import com.epam.jmp.validators.AssignmentDTOValidator;

@Controller
@RequestMapping(ASSIGNMENT_API_MAPPING)
public class AssignmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(AssignmentController.class);
	
	@Autowired
	private PhaseParticipantAssignmentService assignmentService;
	
	@Autowired
	private AssignmentDTOValidator assignmentValidator;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<GenericCollectonDTO<PhaseParticipantAssignmentDTO>> listAllPhaseParticipantAssignments() {
		GenericCollectonDTO<PhaseParticipantAssignmentDTO> dtos = new GenericCollectonDTO<PhaseParticipantAssignmentDTO>();
		List<PhaseParticipantAssignment> assignments = assignmentService.getAll();
		if (assignments.isEmpty()) {
			return new ResponseEntity<GenericCollectonDTO<PhaseParticipantAssignmentDTO>>(HttpStatus.NO_CONTENT);
		} else {
			dtos.setElements(assignments.stream().map(a -> mapper.map(a, PhaseParticipantAssignmentDTO.class))
					.collect(Collectors.toList()));
		}
		return new ResponseEntity<GenericCollectonDTO<PhaseParticipantAssignmentDTO>>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.GET)
	public ResponseEntity<PhaseParticipantAssignmentDTO> getPhaseParticipantAssignment(
			@PathVariable("uid") String uid) {
		PhaseParticipantAssignment assignment = assignmentService.getByUid(uid);
		if (assignment == null) {
			return new ResponseEntity<PhaseParticipantAssignmentDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PhaseParticipantAssignmentDTO>(
				mapper.map(assignment, PhaseParticipantAssignmentDTO.class), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createPhaseParticipantAssignment(HttpServletRequest request,
			@RequestBody @Valid PhaseParticipantAssignmentDTO dto, UriComponentsBuilder ucBuilder) {
		String uid = assignmentService.create(mapper.map(dto, PhaseParticipantAssignment.class));
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path(ASSIGNMENTS_API + "/{id}").buildAndExpand(uid).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.PUT)
	public ResponseEntity<PhaseParticipantAssignmentDTO> updatePhaseParticipantAssignment(HttpServletRequest request,
			@PathVariable("uid") String uid, @RequestBody @Valid PhaseParticipantAssignmentDTO dto) {
		
		PhaseParticipantAssignment currentAssignment = assignmentService.getByUid(uid);
		if (currentAssignment == null) {
			logger.debug("PhaseParticipantAssignment with uid " + uid + " not found");
			return new ResponseEntity<PhaseParticipantAssignmentDTO>(HttpStatus.NOT_FOUND);
		}
		mapper.map(dto, currentAssignment);
		currentAssignment = assignmentService.update(currentAssignment);
		return new ResponseEntity<PhaseParticipantAssignmentDTO>(
				mapper.map(currentAssignment, PhaseParticipantAssignmentDTO.class), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePhaseParticipantAssignment(@PathVariable("uid") String uid) {
		
		PhaseParticipantAssignment assignment = assignmentService.getByUid(uid);
		if (assignment == null) {
			logger.debug("Unable to delete. PhaseParticipantAssignment with uid " + uid + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		binder.addValidators(this.assignmentValidator);
	}
	
}
