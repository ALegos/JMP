package com.epam.jmp.controller;

import static com.epam.jmp.controller.ControllerConstants.PERSON_API_MAPPING;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import com.epam.jmp.model.Person;
import com.epam.jmp.service.PersonService;
import com.epam.jmp.validators.EmailValidator;
import com.epam.jmp.validators.PersonValidator;

@Controller
@RequestMapping(PERSON_API_MAPPING)
public class PersonController {
	
	@Autowired
	public PersonValidator personValidator;
	
	@Autowired
	public EmailValidator emailValidator;
	
	@Autowired
	public PersonService personService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Person>> listAllPerson() {
		List<Person> persons = personService.getAll();
		if (persons.isEmpty()) {
			return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, params = "email")
	public ResponseEntity<Person> findByEmail(@RequestParam("email") String email) {
		// TODO remove sysout
		System.out.println("Fetching Persons with email " + email);
		Optional<Person> person = personService.findByEmail(email);
		if (!person.isPresent()) {
			// TODO remove sysout
			System.out.println("Person with email " + email + " not found");
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(person.get(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.GET)
	public ResponseEntity<Person> getUser(@PathVariable("uid") String uid) {
		// TODO remove sysout
		System.out.println("Fetching Person with uid " + uid);
		Person person = personService.getByUid(uid);
		if (person == null) {
			// TODO remove sysout
			System.out.println("Person with uid " + uid + " not found");
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
	
	// TODO User validation
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createPerson(@RequestBody Person person, UriComponentsBuilder ucBuilder) {
		// TODO remove sysout
		System.out.println("Creating Person " + person.getName());
		if (personService.isPersonExist(person)) {
			// TODO remove sysout
			System.out.println("A Person with email " + person.getEmail() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		personService.create(person);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/person/{id}").buildAndExpand(person.getUid()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.PUT)
	public ResponseEntity<Person> updatePerson(@PathVariable("uid") String uid, @RequestBody Person person) {
		// TODO remove sysout
		System.out.println("Updating Person " + uid);
		Person currentPerson = personService.getByUid(uid);
		
		if (currentPerson == null) {
			// TODO remove sysout
			System.out.println("Person with uid " + uid + " not found");
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		
		currentPerson.setName(person.getName());
		currentPerson.setLevel(person.getLevel());
		currentPerson.setPrimarySkill(person.getPrimarySkill());
		currentPerson.setBirthDate(person.getBirthDate());
		// TODO update email validation
		
		personService.update(currentPerson);
		return new ResponseEntity<Person>(currentPerson, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.DELETE)
	public ResponseEntity<Person> deletePerson(@PathVariable("uid") String uid) {
		// TODO remove sysout
		System.out.println("Fetching & Deleting Person with uid " + uid);
		
		Person user = personService.getByUid(uid);
		if (user == null) {
			// TODO remove sysout
			System.out.println("Unable to delete. Person with uid " + uid + " not found");
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		
		personService.delete(uid);
		return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
	}
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		binder.addValidators(this.personValidator, this.emailValidator);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "birthDate", new CustomDateEditor(dateFormat, true));
	}
	
}
