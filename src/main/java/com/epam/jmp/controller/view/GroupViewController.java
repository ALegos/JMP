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
import com.epam.jmp.dto.GroupDTO;
import com.epam.jmp.model.Group;
import com.epam.jmp.service.GroupService;

@Controller
public class GroupViewController {
	
	@Autowired
	public GroupService groupService;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(value = "/group/create", method = RequestMethod.GET)
	public String createGroup(Model model) {
		model.addAttribute("groupDTO", new GroupDTO());
		return "group/create";
	}
	
	@RequestMapping(value = "/group/create", method = RequestMethod.POST)
	public String createGroup(HttpServletRequest request, @ModelAttribute @Valid GroupDTO groupDTO,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "group/create";
		} else {
			this.groupService.create(mapper.map(groupDTO, Group.class));
			return "redirect:/groups";
		}
	}
	
	@RequestMapping(value = "/group/{uid}", method = RequestMethod.GET)
	public String updateGroup(@PathVariable("uid") String uid, Model model) {
		Group group = groupService.getByUid(uid);
		if (group != null) {
			model.addAttribute("groupDTO", mapper.map(group, GroupDTO.class));
			return "group/update";
		} else {
			return "redirect:/groups";
		}
	}
	
	@RequestMapping(value = "/group/{uid}", method = RequestMethod.POST)
	public String updateGroup(HttpServletRequest request, @ModelAttribute @Valid GroupDTO groupDTO,
			BindingResult bindingResult, ModelMap modelMap) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("groupDTO", groupDTO);
			return "group/update";
		} else {
			this.groupService.update(mapper.map(groupDTO, Group.class));
			return "redirect:/groups";
		}
	}
	
	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public String showAllGroup(Model model) {
		model.addAttribute("dateFormatPattern", DATE_FORMAT_PATTERN);
		model.addAttribute("dateTimeFormatPattern", DATETIME_FORMAT_PATTERN);
		GenericCollectonDTO<GroupDTO> dtos = new GenericCollectonDTO<GroupDTO>(this.groupService.getAll().stream()
				.map(p -> mapper.map(p, GroupDTO.class)).collect(Collectors.toList()));
		model.addAttribute("groups", dtos);
		return "groups";
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
