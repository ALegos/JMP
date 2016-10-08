package com.epam.jmp.controller;

import static com.epam.jmp.constants.UtilConstants.DATE_FORMAT_PATTERN;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.epam.jmp.model.Person;
import com.epam.jmp.model.enums.Level;
import com.epam.jmp.service.PersonService;
import com.epam.jmp.validators.PersonValidator;

@Controller
public class PersonViewController {
	
	@Autowired
	public PersonValidator personValidator;
	
	@Autowired
	public PersonService personService;
	
	@RequestMapping(value = "/person/create", method = RequestMethod.GET)
	public String createPerson(Model model) {
		model.addAttribute("person", new Person());
		return "person/create";
	}
	
	@RequestMapping(value = "/person/create", method = RequestMethod.POST)
	public String createPerson(HttpServletRequest request, @ModelAttribute @Valid Person person,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "person/create";
		} else {
			this.personService.create(person);
			return "redirect:/persons";
		}
	}
	
	@RequestMapping(value = "/person/{uid}", method = RequestMethod.GET)
	public String updatePerson(@PathVariable("uid") String uid, Model model) {
		Person person = personService.getByUid(uid);
		if (person != null) {
			model.addAttribute("person", person);
			return "person/update";
		} else {
			return "redirect:/persons";
		}
	}
	
	@RequestMapping(value = "/person/{uid}", method = RequestMethod.POST)
	public String updatePerson(HttpServletRequest request, @ModelAttribute @Valid Person person,
			BindingResult bindingResult, ModelMap modelMap) {
		if (bindingResult.hasErrors()) {
			return "person/update";
		} else {
			this.personService.update(person);
			return "redirect:/persons";
		}
	}
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String showAllPerson(Model model) {
		model.addAttribute("dateFormatPattern", DATE_FORMAT_PATTERN);
		model.addAttribute("persons", this.personService.getAll());
		return "persons";
	}
	
	@PostConstruct
	private void init() {
		Person p1 = new Person();
		p1.setName("Oleh.Hupalo");
		p1.setEmail("oleh.hupalo.jmptesting@com");
		p1.setPrimarySkill("Java");
		p1.setLevel(Level.L2);
		p1.setBirthDate(Date.from(LocalDate.of(1991, 7, 29).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		p1.setManager(null);
		
		Person p2 = new Person();
		p2.setName("Oleh.mezhva");
		p2.setEmail("oleh.mezhva.jmptesting@com");
		p2.setPrimarySkill("MSTR");
		p2.setLevel(Level.L1);
		p2.setBirthDate(
				Date.from(LocalDate.of(1993, 12, 11).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		p2.setManager(null);
		
		Person p3 = new Person();
		p3.setName("Oleksandr.Klymchuk");
		p3.setEmail("oleksandr.klymchuk.jmptesting@com");
		p3.setPrimarySkill("Java");
		p3.setLevel(Level.L3);
		p3.setBirthDate(Date.from(LocalDate.of(1984, 2, 16).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		p3.setManager(null);
		
		personService.create(p1);
		personService.create(p2);
		personService.create(p3);
	}
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		binder.addValidators(this.personValidator);
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "birthDate", new CustomDateEditor(dateFormat, true));
	}
	
}
