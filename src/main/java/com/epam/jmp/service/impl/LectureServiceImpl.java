package com.epam.jmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.jmp.dao.LectureDAO;
import com.epam.jmp.model.Lecture;
import com.epam.jmp.service.LectureService;

@Service
public class LectureServiceImpl extends GenericServiceImpl<Lecture, LectureDAO> implements LectureService {

	@Autowired
	public LectureServiceImpl(LectureDAO lectureDAO) {
		super(lectureDAO);
	}

}
