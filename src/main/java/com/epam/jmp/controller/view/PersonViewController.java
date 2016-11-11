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
import com.epam.jmp.dto.PersonDTO;
import com.epam.jmp.dto.converters.PersonDTOConverter;
import com.epam.jmp.model.Person;
import com.epam.jmp.model.enums.Level;
import com.epam.jmp.model.enums.ParticipantAssignmentStatus;
import com.epam.jmp.model.enums.Role;
import com.epam.jmp.service.MentorshipProgramService;
import com.epam.jmp.service.PersonService;

@Controller
public class PersonViewController {
	
	private PersonService personService;
	private MentorshipProgramService programService;
	private PersonDTOConverter converter;
	
	@Autowired
	public PersonViewController(PersonService personService, PersonDTOConverter converter,
			MentorshipProgramService programService) {
		this.converter = converter;
		this.personService = personService;
		this.programService = programService;
	}
	
	@RequestMapping(value = "/person/create", method = RequestMethod.GET)
	public String createPerson(Model model) {
		model.addAttribute("personDTO", new PersonDTO());
		model.addAttribute("managers", personService.findByIsManagerFlag(true));
		model.addAttribute("levels", Level.values());
		model.addAttribute("programs", programService.findByStartDate(new Date()));
		model.addAttribute("roles", Role.values());
		model.addAttribute("statuses", ParticipantAssignmentStatus.values());
		return "person/create";
	}
	
	@RequestMapping(value = "/person/create", method = RequestMethod.POST)
	public String createPerson(HttpServletRequest request, @ModelAttribute @Valid PersonDTO personDTO,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("managers", personService.findByIsManagerFlag(true));
			model.addAttribute("levels", Level.values());
			model.addAttribute("programs", programService.findByStartDate(new Date()));
			model.addAttribute("roles", Role.values());
			model.addAttribute("statuses", ParticipantAssignmentStatus.values());
			return "person/create";
		} else {
			this.personService.create(converter.toEntity(personDTO));
			return "redirect:/persons";
		}
	}
	
	@RequestMapping(value = "/person/{uid}", method = RequestMethod.GET)
	public String updatePerson(@PathVariable("uid") String uid, Model model) {
		Person person = personService.getByUidWithManagerAndAssignment(uid);
		if (person != null) {
			model.addAttribute("personDTO", converter.toDTO(person));
			model.addAttribute("managers", personService.findByIsManagerFlag(true));
			model.addAttribute("levels", Level.values());
			model.addAttribute("programs", programService.findByStartDate(new Date()));
			model.addAttribute("roles", Role.values());
			model.addAttribute("statuses", ParticipantAssignmentStatus.values());
			return "person/update";
		} else {
			return "redirect:/persons";
		}
	}
	
	@RequestMapping(value = "/person/{uid}", method = RequestMethod.POST)
	public String updatePerson(HttpServletRequest request, @ModelAttribute @Valid PersonDTO personDTO,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("personDTO", personDTO);
			model.addAttribute("managers", personService.findByIsManagerFlag(true));
			model.addAttribute("levels", Level.values());
			model.addAttribute("programs", programService.findByStartDate(new Date()));
			model.addAttribute("roles", Role.values());
			model.addAttribute("statuses", ParticipantAssignmentStatus.values());
			return "person/update";
		} else {
			Person entity = converter.toEntity(personDTO);
			// if (entity.getAssignment() == null &&
			// personDTO.getAssignmentDTO() != null
			// &&
			// StringUtils.isBlank(personDTO.getAssignmentDTO().getPersonUid()))
			// {
			// assignmentService.delete(personDTO.getAssignmentDTO().getUid());
			// }
			this.personService.update(entity);
			return "redirect:/persons";
		}
	}
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String showAllPerson(Model model) {
		model.addAttribute("dateFormatPattern", DATE_FORMAT_PATTERN);
		model.addAttribute("dateTimeFormatPattern", DATETIME_FORMAT_PATTERN);
		GenericCollectonDTO<PersonDTO> dtos = new GenericCollectonDTO<PersonDTO>(
				this.personService.getAll().stream().map(converter::toDTO).collect(Collectors.toList()));
		model.addAttribute("persons", dtos);
		return "persons";
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
