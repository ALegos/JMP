package com.epam.jmp.controller;

import static com.epam.jmp.constants.UtilConstants.DATE_FORMAT_PATTERN;
import static com.epam.jmp.constants.UtilConstants.DURATION_FORMAT_PATTERN;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	@RequestMapping(value = { "/", "persons" }, method = RequestMethod.GET)
	public String getPersonsList() {
		return "redirect:/person/list";
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
