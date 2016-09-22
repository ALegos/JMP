package com.epam.jmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.jmp.dao.LectureDAO;
import com.epam.jmp.model.Lecture;
import com.epam.jmp.service.LectureService;

@Service
public class LectureServiceImpl implements LectureService{

	@Autowired
	private LectureDAO lectureDAO;
	
	@Override
	public Lecture getByUid(long uid) {
		return lectureDAO.getByUid(uid);
	}

	@Override
	public List<Lecture> getAll() {
		return lectureDAO.getAll();
	}

	@Override
	public Lecture create(Lecture t) {
		return lectureDAO.create(t);
	}

	@Override
	public Lecture update(Lecture t) {
		return lectureDAO.update(t);
	}

	@Override
	public void delete(long uid) {
		lectureDAO.delete(uid);
	}

}
