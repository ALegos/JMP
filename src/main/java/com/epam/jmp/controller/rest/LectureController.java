package com.epam.jmp.controller.rest;

import static com.epam.jmp.constants.ControllerConstants.LECTURES_API;
import static com.epam.jmp.constants.ControllerConstants.LECTURE_API_MAPPING;

import java.util.List;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.epam.jmp.dto.GenericCollectonDTO;
import com.epam.jmp.dto.LectureDTO;
import com.epam.jmp.model.Lecture;
import com.epam.jmp.service.LectureService;
import com.epam.jmp.validators.LectureDTOValidator;

@Controller
@RequestMapping(LECTURE_API_MAPPING)
public class LectureController {
	
	private static final Logger logger = LoggerFactory.getLogger(LectureController.class);
	
	@Autowired
	private LectureService lectureService;
	
	@Autowired
	private LectureDTOValidator lectureValidator;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<GenericCollectonDTO<LectureDTO>> listAllLectures() {
		GenericCollectonDTO<LectureDTO> dtos = new GenericCollectonDTO<LectureDTO>();
		List<Lecture> lectures = lectureService.getAll();
		if (lectures.isEmpty()) {
			return new ResponseEntity<GenericCollectonDTO<LectureDTO>>(HttpStatus.NO_CONTENT);
		} else {
			dtos.setElements(lectures.stream().map(l -> mapper.map(l, LectureDTO.class)).collect(Collectors.toList()));
		}
		return new ResponseEntity<GenericCollectonDTO<LectureDTO>>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.GET)
	public ResponseEntity<LectureDTO> getLecture(@PathVariable("uid") String uid) {
		Lecture lecture = lectureService.getByUid(uid);
		if (lecture == null) {
			return new ResponseEntity<LectureDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<LectureDTO>(mapper.map(lecture, LectureDTO.class), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createLecture(HttpServletRequest request, @RequestBody @Valid LectureDTO dto,
			UriComponentsBuilder ucBuilder) {
		String uid = lectureService.create(mapper.map(dto, Lecture.class));
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path(LECTURES_API + "/{id}").buildAndExpand(uid).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.PUT)
	public ResponseEntity<LectureDTO> updateLecture(HttpServletRequest request, @PathVariable("uid") String uid,
			@RequestBody @Valid LectureDTO dto) {
		
		Lecture currentLecture = lectureService.getByUid(uid);
		if (currentLecture == null) {
			logger.debug("Lecture with uid " + uid + " not found");
			return new ResponseEntity<LectureDTO>(HttpStatus.NOT_FOUND);
		}
		mapper.map(dto, currentLecture);
		currentLecture = lectureService.update(currentLecture);
		return new ResponseEntity<LectureDTO>(mapper.map(currentLecture, LectureDTO.class), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{uid}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteLecture(@PathVariable("uid") String uid) {
		
		Lecture lecture = lectureService.getByUid(uid);
		if (lecture == null) {
			logger.debug("Unable to delete. Lecture with uid " + uid + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		binder.addValidators(this.lectureValidator);
	}
	
}
