package com.epam.jmp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.epam.jmp.model.Person;
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
	public String createPerson(@ModelAttribute Person person, UriComponentsBuilder ucBuilder,
			BindingResult bindingResult) {
		this.personService.create(person);
		if (bindingResult.hasErrors()) {
			return "create";
		} else {
			return "persons";
		}
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

	@RequestMapping(value = "/{uid}", method = RequestMethod.DELETE)
	public void deletePerson(@PathVariable String uid, Model model) {
		this.personService.delete(uid);
	}

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		// binder.addValidators(userValidator, emailValidator);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "birthDate", new CustomDateEditor(dateFormat, true));
	}

}
