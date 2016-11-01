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
import com.epam.jmp.dto.LectureDTO;
import com.epam.jmp.model.Lecture;
import com.epam.jmp.service.LectureService;

@Controller
public class LecturesViewController {
	
	@Autowired
	public LectureService lectureService;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(value = "/lecture/create", method = RequestMethod.GET)
	public String createLecture(Model model) {
		model.addAttribute("lectureDTO", new LectureDTO());
		return "lecture/create";
	}
	
	@RequestMapping(value = "/lecture/create", method = RequestMethod.POST)
	public String createLecture(HttpServletRequest request, @ModelAttribute @Valid LectureDTO lectureDTO,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "lecture/create";
		} else {
			this.lectureService.create(mapper.map(lectureDTO, Lecture.class));
			return "redirect:/lectures";
		}
	}
	
	@RequestMapping(value = "/lecture/{uid}", method = RequestMethod.GET)
	public String updateLecture(@PathVariable("uid") String uid, Model model) {
		Lecture lecture = lectureService.getByUid(uid);
		if (lecture != null) {
			model.addAttribute("lectureDTO", mapper.map(lecture, LectureDTO.class));
			return "lecture/update";
		} else {
			return "redirect:/lectures";
		}
	}
	
	@RequestMapping(value = "/lecture/{uid}", method = RequestMethod.POST)
	public String updateLecture(HttpServletRequest request, @ModelAttribute @Valid LectureDTO lectureDTO,
			BindingResult bindingResult, ModelMap modelMap) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("lectureDTO", lectureDTO);
			return "lecture/update";
		} else {
			this.lectureService.update(mapper.map(lectureDTO, Lecture.class));
			return "redirect:/lectures";
		}
	}
	
	@RequestMapping(value = "/lectures", method = RequestMethod.GET)
	public String showAllLecture(Model model) {
		model.addAttribute("durationFormatPattern", DURATION_FORMAT_PATTERN);
		model.addAttribute("dateTimeFormatPattern", DATETIME_FORMAT_PATTERN);
		GenericCollectonDTO<LectureDTO> dtos = new GenericCollectonDTO<LectureDTO>(this.lectureService.getAll().stream()
				.map(p -> mapper.map(p, LectureDTO.class)).collect(Collectors.toList()));
		model.addAttribute("lectures", dtos);
		return "lectures";
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
