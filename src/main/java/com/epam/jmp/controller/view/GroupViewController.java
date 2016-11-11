package com.epam.jmp.controller.view;

import static com.epam.jmp.constants.UtilConstants.DATETIME_FORMAT_PATTERN;
import static com.epam.jmp.constants.UtilConstants.DATE_FORMAT_PATTERN;
import static com.epam.jmp.constants.UtilConstants.DURATION_FORMAT_PATTERN;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.jmp.dto.GenericCollectonDTO;
import com.epam.jmp.dto.GroupDTO;
import com.epam.jmp.dto.converters.GroupDTOConverter;
import com.epam.jmp.model.Group;
import com.epam.jmp.model.enums.Status;
import com.epam.jmp.service.GroupService;
import com.epam.jmp.service.MentorshipProgramService;
import com.epam.jmp.service.PersonService;

@Controller
public class GroupViewController {
	
	private GroupService groupService;
	private PersonService personService;
	private MentorshipProgramService programService;
	private GroupDTOConverter converter;
	
	@Autowired
	public GroupViewController(GroupService groupService, GroupDTOConverter converter, PersonService personService,
			MentorshipProgramService programService) {
		this.groupService = groupService;
		this.converter = converter;
		this.personService = personService;
		this.programService = programService;
	}
	
	@RequestMapping(value = "/group/create", method = RequestMethod.GET)
	public String createGroup(Model model) {
		model.addAttribute("groupDTO", new GroupDTO());
		// TODO use only mentees without assigned groups
		model.addAttribute("mentees", personService.getAll());
		// TODO use only menteors
		model.addAttribute("mentors", personService.getAll());
		// TODO use only not ended programs
		model.addAttribute("programs", programService.findByStartDate(new Date()));
		model.addAttribute("statuses", Status.values());
		
		return "group/create";
	}
	
	@RequestMapping(value = "/group/create", method = RequestMethod.POST)
	public String createGroup(HttpServletRequest request, @ModelAttribute @Valid GroupDTO groupDTO,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			// TODO use only mentees without assigned groups
			model.addAttribute("mentees", personService.getAll());
			// TODO use only menteors
			model.addAttribute("mentors", personService.getAll());
			// TODO use only not ended programs
			model.addAttribute("programs", programService.findByStartDate(new Date()));
			model.addAttribute("statuses", Status.values());
			return "group/create";
		} else {
			this.groupService.create(converter.toEntity(groupDTO));
			return "redirect:/groups";
		}
	}
	
	@RequestMapping(value = "/group/{uid}", method = RequestMethod.GET)
	public String updateGroup(@PathVariable("uid") String uid, Model model) {
		Group group = groupService.getByUid(uid);
		if (group != null) {
			model.addAttribute("groupDTO", converter.toDTO(group));
			// TODO use only mentees without assigned groups
			model.addAttribute("mentees", personService.getAll());
			// TODO use only menteors
			model.addAttribute("mentors", personService.getAll());
			// TODO use only not ended programs
			model.addAttribute("programs", programService.findByStartDate(new Date()));
			model.addAttribute("statuses", Status.values());
			return "group/update";
		} else {
			return "redirect:/groups";
		}
	}
	
	@RequestMapping(value = "/group/{uid}", method = RequestMethod.POST)
	public String updateGroup(HttpServletRequest request, @ModelAttribute @Valid GroupDTO groupDTO,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("groupDTO", groupDTO);
			// TODO use only mentees without assigned groups
			model.addAttribute("mentees", personService.getAll());
			// TODO use only menteors
			model.addAttribute("mentors", personService.getAll());
			// TODO use only not ended programs
			model.addAttribute("programs", programService.findByStartDate(new Date()));
			model.addAttribute("statuses", Status.values());
			return "group/update";
		} else {
			this.groupService.update(converter.toEntity(groupDTO));
			return "redirect:/groups";
		}
	}
	
	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public String showAllGroup(Model model) {
		model.addAttribute("dateFormatPattern", DATE_FORMAT_PATTERN);
		model.addAttribute("dateTimeFormatPattern", DATETIME_FORMAT_PATTERN);
		GenericCollectonDTO<GroupDTO> dtos = new GenericCollectonDTO<GroupDTO>(
				this.groupService.getAll().stream().map(converter::toDTO).collect(Collectors.toList()));
		model.addAttribute("groups", dtos);
		return "groups";
	}
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		// TODO add custom date time format for lecture start & end
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		dateFormat.setLenient(false);
		SimpleDateFormat durationFormat = new SimpleDateFormat(DURATION_FORMAT_PATTERN);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Duration.class, new CustomDateEditor(durationFormat, true));
	}
	
}
