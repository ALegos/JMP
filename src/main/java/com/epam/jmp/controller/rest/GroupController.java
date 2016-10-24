package com.epam.jmp.controller.rest;

import static com.epam.jmp.constants.ControllerConstants.GROUPS_API;
import static com.epam.jmp.constants.ControllerConstants.GROUP_API_MAPPING;

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
import com.epam.jmp.dto.GroupDTO;
import com.epam.jmp.model.Group;
import com.epam.jmp.service.GroupService;
import com.epam.jmp.validators.GroupDTOValidator;

@Controller
@RequestMapping(GROUP_API_MAPPING)
public class GroupController {
	
	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private GroupDTOValidator groupValidator;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<GenericCollectonDTO<GroupDTO>> listAllGroups() {
		GenericCollectonDTO<GroupDTO> dtos = new GenericCollectonDTO<GroupDTO>();
		List<Group> groups = groupService.getAll();
		if (groups.isEmpty()) {
			return new ResponseEntity<GenericCollectonDTO<GroupDTO>>(HttpStatus.NO_CONTENT);
		} else {
			dtos.setElements(groups.stream().map(g -> mapper.map(g, GroupDTO.class)).collect(Collectors.toList()));
		}
		return new ResponseEntity<GenericCollectonDTO<GroupDTO>>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.GET)
	public ResponseEntity<GroupDTO> getGroup(@PathVariable("uid") String uid) {
		Group group = groupService.getByUid(uid);
		if (group == null) {
			return new ResponseEntity<GroupDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<GroupDTO>(mapper.map(group, GroupDTO.class), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createGroup(HttpServletRequest request, @RequestBody @Valid GroupDTO dto,
			UriComponentsBuilder ucBuilder) {
		String uid = groupService.create(mapper.map(dto, Group.class));
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path(GROUPS_API + "/{id}").buildAndExpand(uid).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.PUT)
	public ResponseEntity<GroupDTO> updateGroup(HttpServletRequest request, @PathVariable("uid") String uid,
			@RequestBody @Valid GroupDTO dto) {
		
		Group currentGroup = groupService.getByUid(uid);
		if (currentGroup == null) {
			logger.debug("Group with uid " + uid + " not found");
			return new ResponseEntity<GroupDTO>(HttpStatus.NOT_FOUND);
		}
		mapper.map(dto, currentGroup);
		currentGroup = groupService.update(currentGroup);
		return new ResponseEntity<GroupDTO>(mapper.map(currentGroup, GroupDTO.class), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteGroup(@PathVariable("uid") String uid) {
		
		Group group = groupService.getByUid(uid);
		if (group == null) {
			logger.debug("Unable to delete. Group with uid " + uid + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		binder.addValidators(this.groupValidator);
	}
	
}
