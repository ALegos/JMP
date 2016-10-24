package com.epam.jmp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.epam.jmp.dto.LectureDTO;

@Component
public class LectureDTOValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LectureDTO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}
	
}
