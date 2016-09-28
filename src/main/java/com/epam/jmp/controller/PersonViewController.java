package com.epam.jmp.controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.jmp.model.Person;
import com.epam.jmp.model.enums.Level;
import com.epam.jmp.service.PersonService;
import com.epam.jmp.validators.EmailValidator;
import com.epam.jmp.validators.PersonValidator;

@Controller
public class PersonViewController {
	
	@Autowired
	public PersonValidator personValidator;
	
	@Autowired
	public EmailValidator emailValidator;
	
	@Autowired
	public PersonService personService;
	
	// TODO User validation
	@RequestMapping(value = "/person/create", method = RequestMethod.POST)
	public String createPerson(@ModelAttribute @Valid Person person, BindingResult bindingResult, ModelMap modelMap) {
		this.personService.create(person);
		if (bindingResult.hasErrors()) {
			return "create";
		} else {
			return "redirect:/persons";
		}
	}
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String showAllPerson(Model model) {
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
		p1.setBirthDate(new Date(1991, 7, 29));
		p1.setManager(null);
		
		Person p2 = new Person();
		p2.setName("Oleh.mezhva");
		p2.setEmail("oleh.mezhva.jmptesting@com");
		p2.setPrimarySkill("MSTR");
		p2.setLevel(Level.L1);
		p2.setBirthDate(new Date(1993, 12, 11));
		p2.setManager(null);
		
		Person p3 = new Person();
		p3.setName("Oleksandr.Klymchuk");
		p3.setEmail("oleksandr.klymchuk.jmptesting@com");
		p3.setPrimarySkill("Java");
		p3.setLevel(Level.L3);
		p3.setBirthDate(new Date(1984, 2, 16));
		p3.setManager(null);
		
		personService.create(p1);
		personService.create(p2);
		personService.create(p3);
	}
	
}
