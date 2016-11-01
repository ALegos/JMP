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

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.jmp.dto.GenericCollectonDTO;
import com.epam.jmp.dto.PersonDTO;
import com.epam.jmp.model.Person;
import com.epam.jmp.service.PersonService;

@Controller
public class PersonViewController {
	
	@Autowired
	public PersonService personService;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(value = "/person/create", method = RequestMethod.GET)
	public String createPerson(Model model) {
		model.addAttribute("personDTO", new PersonDTO());
		return "person/create";
	}
	
	@RequestMapping(value = "/person/create", method = RequestMethod.POST)
	public String createPerson(HttpServletRequest request, @ModelAttribute @Valid PersonDTO personDTO,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "person/create";
		} else {
			this.personService.create(mapper.map(personDTO, Person.class));
			return "redirect:/persons";
		}
	}
	
	@RequestMapping(value = "/person/{uid}", method = RequestMethod.GET)
	public String updatePerson(@PathVariable("uid") String uid, Model model) {
		Person person = personService.getByUid(uid);
		if (person != null) {
			model.addAttribute("personDTO", mapper.map(person, PersonDTO.class));
			return "person/update";
		} else {
			return "redirect:/persons";
		}
	}
	
	@RequestMapping(value = "/person/{uid}", method = RequestMethod.POST)
	public String updatePerson(HttpServletRequest request, @ModelAttribute @Valid PersonDTO personDTO,
			BindingResult bindingResult, ModelMap modelMap) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("personDTO", personDTO);
			return "person/update";
		} else {
			this.personService.update(mapper.map(personDTO, Person.class));
			return "redirect:/persons";
		}
	}
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String showAllPerson(Model model) {
		model.addAttribute("dateFormatPattern", DATE_FORMAT_PATTERN);
		model.addAttribute("dateTimeFormatPattern", DATETIME_FORMAT_PATTERN);
		GenericCollectonDTO<PersonDTO> dtos = new GenericCollectonDTO<PersonDTO>(this.personService.getAll().stream()
				.map(p -> mapper.map(p, PersonDTO.class)).collect(Collectors.toList()));
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
