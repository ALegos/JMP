package com.epam.jmp.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.epam.jmp.model.Person;
import com.epam.jmp.model.enums.Level;
import com.epam.jmp.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	public PersonService personService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public Model creteView(Model model) {
		model.addAttribute("person", new Person());
		return model;
	}

	// TODO User validation
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Void> createPerson(@ModelAttribute Person person, UriComponentsBuilder ucBuilder) {
		this.personService.create(person);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/person/{id}").buildAndExpand(person.getUid()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String showAllUsers(Model model) {
		model.addAttribute("persons", this.personService.getAll());
		return "persons";

	}

	@ResponseBody
	@RequestMapping(value = "/{uid}", method = RequestMethod.GET)
	public Person findPerson(@PathVariable String uid, Model model) {
		return this.personService.getByUid(uid);
	}

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.CREATED)
	public void init() {
		Person test = new Person();
		test.setName("Oleh Hupalo");
		test.setEmail("test@a.com");
		test.setLevel(Level.L2);
		test.setBirgthDate(new GregorianCalendar(1991, Calendar.JULY, 29).getTime());

		test = this.personService.create(test);
		System.out.println(test.getUid());
	}

}
