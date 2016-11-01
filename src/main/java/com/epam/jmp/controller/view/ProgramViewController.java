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
import com.epam.jmp.dto.MentorshipProgramDTO;
import com.epam.jmp.model.MentorshipProgram;
import com.epam.jmp.service.MentorshipProgramService;

@Controller
public class ProgramViewController {
	
	@Autowired
	public MentorshipProgramService programService;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(value = "/program/create", method = RequestMethod.GET)
	public String createPerson(Model model) {
		model.addAttribute("mentorshipProgramDTO", new MentorshipProgramDTO());
		return "program/create";
	}
	
	@RequestMapping(value = "/program/create", method = RequestMethod.POST)
	public String createPerson(HttpServletRequest request,
			@ModelAttribute @Valid MentorshipProgramDTO mentorshipProgramDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "program/create";
		} else {
			this.programService.create(mapper.map(mentorshipProgramDTO, MentorshipProgram.class));
			return "redirect:/programs";
		}
	}
	
	@RequestMapping(value = "/program/{uid}", method = RequestMethod.GET)
	public String updatePerson(@PathVariable("uid") String uid, Model model) {
		MentorshipProgram program = programService.getByUid(uid);
		if (program != null) {
			model.addAttribute("mentorshipProgramDTO", mapper.map(program, MentorshipProgramDTO.class));
			return "program/update";
		} else {
			return "redirect:/programs";
		}
	}
	
	@RequestMapping(value = "/program/{uid}", method = RequestMethod.POST)
	public String updatePerson(HttpServletRequest request,
			@ModelAttribute @Valid MentorshipProgramDTO mentorshipProgramDTO, BindingResult bindingResult,
			ModelMap modelMap) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("programDTO", mentorshipProgramDTO);
			return "program/update";
		} else {
			this.programService.update(mapper.map(mentorshipProgramDTO, MentorshipProgram.class));
			return "redirect:/programs";
		}
	}
	
	@RequestMapping(value = "/programs", method = RequestMethod.GET)
	public String showAllPerson(Model model) {
		model.addAttribute("dateFormatPattern", DATE_FORMAT_PATTERN);
		model.addAttribute("dateTimeFormatPattern", DATETIME_FORMAT_PATTERN);
		GenericCollectonDTO<MentorshipProgramDTO> dtos = new GenericCollectonDTO<MentorshipProgramDTO>(
				this.programService.getAll().stream().map(p -> mapper.map(p, MentorshipProgramDTO.class))
						.collect(Collectors.toList()));
		model.addAttribute("programs", dtos);
		return "programs";
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
