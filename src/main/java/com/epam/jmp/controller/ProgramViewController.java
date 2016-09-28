package com.epam.jmp.controller;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.jmp.model.MentorshipProgram;
import com.epam.jmp.service.MentorshipProgramService;

@Controller
public class ProgramViewController {
	
	@Autowired
	public MentorshipProgramService programService;
	
	// TODO User validation
	@RequestMapping(value = "/program/create", method = RequestMethod.POST)
	public String createPerson(@ModelAttribute MentorshipProgram program, BindingResult bindingResult,
			ModelMap modelMap) {
		programService.create(program);
		if (bindingResult.hasErrors()) {
			return "create";
		} else {
			return "redirect:/programs";
		}
	}
	
	@RequestMapping(value = "/programs", method = RequestMethod.GET)
	public String showAllPerson(Model model) {
		model.addAttribute("programs", programService.getAll());
		return "programs";
	}
	
	@PostConstruct
	private void init() {
		MentorshipProgram m1 = new MentorshipProgram();
		m1.setName("Javascript program");
		m1.setStartDate(new Date(2016, 9, 27));
		m1.setEndDate(new Date(2016, 12, 10));
		m1.setOfficeLocation("Lviv");
		m1.setGroups(null);
		
		MentorshipProgram m2 = new MentorshipProgram();
		m2.setName("PHP for beginers");
		m2.setStartDate(new Date(2016, 10, 1));
		m2.setEndDate(new Date(2016, 11, 13));
		m2.setOfficeLocation("Kyiv");
		m2.setGroups(null);
		
		MentorshipProgram m3 = new MentorshipProgram();
		m3.setName("PHP basics");
		m3.setStartDate(new Date(2016, 7, 4));
		m3.setEndDate(new Date(2016, 11, 29));
		m3.setOfficeLocation("Lviv");
		m3.setGroups(null);
		
		programService.create(m1);
		programService.create(m2);
		programService.create(m3);
	}
	
}
