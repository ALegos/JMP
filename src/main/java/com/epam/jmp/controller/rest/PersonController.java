package com.epam.jmp.controller.rest;

import static com.epam.jmp.constants.ControllerConstants.PERSONS_API;
import static com.epam.jmp.constants.ControllerConstants.PERSON_API_MAPPING;

import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.epam.jmp.dto.GenericCollectonDTO;
import com.epam.jmp.dto.PersonDTO;
import com.epam.jmp.model.Person;
import com.epam.jmp.service.PersonService;
import com.epam.jmp.validators.PersonDTOValidator;

@Controller
@RequestMapping(PERSON_API_MAPPING)
public class PersonController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private PersonDTOValidator personValidator;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<GenericCollectonDTO<PersonDTO>> listAllPerson() {
		GenericCollectonDTO<PersonDTO> dtos = new GenericCollectonDTO<PersonDTO>();
		List<Person> persons = personService.getAll();
		if (persons.isEmpty()) {
			return new ResponseEntity<GenericCollectonDTO<PersonDTO>>(HttpStatus.NO_CONTENT);
		} else {
			dtos.setElements(persons.stream().map(p -> mapper.map(p, PersonDTO.class)).collect(Collectors.toList()));
		}
		return new ResponseEntity<GenericCollectonDTO<PersonDTO>>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, params = "email")
	public ResponseEntity<PersonDTO> findByEmail(@RequestParam("email") String email) {
		Optional<Person> person = personService.findByEmail(email);
		if (!person.isPresent()) {
			return new ResponseEntity<PersonDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PersonDTO>(mapper.map(person.get(), PersonDTO.class), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.GET)
	public ResponseEntity<PersonDTO> getUser(@PathVariable("uid") String uid) {
		Person person = personService.getByUid(uid);
		if (person == null) {
			return new ResponseEntity<PersonDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PersonDTO>(mapper.map(person, PersonDTO.class), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createPerson(HttpServletRequest request, @RequestBody @Valid PersonDTO dto,
			UriComponentsBuilder ucBuilder) {
		if (personService.isPersonExist(dto.getEmail())) {
			logger.debug("A Person with email " + dto.getEmail() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		String uid = personService.create(mapper.map(dto, Person.class));
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path(PERSONS_API + "/{id}").buildAndExpand(uid).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.PUT)
	public ResponseEntity<PersonDTO> updatePerson(HttpServletRequest request, @PathVariable("uid") String uid,
			@RequestBody @Valid PersonDTO dto) {
		
		Person currentPerson = personService.getByUid(uid);
		
		if (currentPerson == null) {
			logger.debug("Person with uid " + uid + " not found");
			return new ResponseEntity<PersonDTO>(HttpStatus.NOT_FOUND);
		}
		currentPerson.setName(dto.getName());
		currentPerson.setLevel(dto.getLevel());
		currentPerson.setExcluded(dto.getExcluded());
		currentPerson.setPrimarySkill(dto.getPrimarySkill());
		currentPerson.setBirthDate(dto.getBirthDate());
		// TODO all fields should be updated
		
		currentPerson = personService.update(currentPerson);
		return new ResponseEntity<PersonDTO>(mapper.map(currentPerson, PersonDTO.class), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePerson(@PathVariable("uid") String uid) {
		
		Person user = personService.getByUid(uid);
		if (user == null) {
			logger.debug("Unable to delete. Person with uid " + uid + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		personService.delete(uid);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		binder.addValidators(this.personValidator);
	}
	
}
