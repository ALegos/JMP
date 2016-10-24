package com.epam.jmp.controller.view;

import static com.epam.jmp.constants.UtilConstants.DATE_FORMAT_PATTERN;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
			return "person/create";
		} else {
			return "redirect:/programs";
		}
	}
	
	@RequestMapping(value = "/programs", method = RequestMethod.GET)
	public String showAllPerson(Model model) {
		model.addAttribute("dateFormatPattern", DATE_FORMAT_PATTERN);
		model.addAttribute("programs", programService.getAll());
		return "programs";
	}
	
	// @PostConstruct
	private void init() {
		MentorshipProgram m1 = new MentorshipProgram();
		m1.setName("Javascript program");
		m1.setStartDate(Date.from(LocalDate.of(2016, 9, 27).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		m1.setEndDate(Date.from(LocalDate.of(2016, 12, 10).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		m1.setOfficeLocation("Lviv");
		m1.setGroups(null);
		
		MentorshipProgram m2 = new MentorshipProgram();
		m2.setName("PHP for beginers");
		m2.setStartDate(Date.from(LocalDate.of(2016, 10, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		m2.setEndDate(Date.from(LocalDate.of(2016, 11, 13).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		m2.setOfficeLocation("Kyiv");
		m2.setGroups(null);
		
		MentorshipProgram m3 = new MentorshipProgram();
		m3.setName("PHP basics");
		m3.setStartDate(Date.from(LocalDate.of(2016, 7, 4).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		m3.setEndDate(Date.from(LocalDate.of(2016, 11, 29).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		m3.setOfficeLocation("Lviv");
		m3.setGroups(null);
		
		programService.create(m1);
		programService.create(m2);
		programService.create(m3);
	}
	
}
