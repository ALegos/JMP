package com.epam.jmp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.jmp.service.MentorshipProgramService;

@Controller
public class PersonViewController {
	
	@Autowired
	public MentorshipProgramService programService;
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String showAllPerson(Model model) {
		model.addAttribute("persons", this.programService.getAll());
		return "persons";
	}
}
