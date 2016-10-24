package com.epam.jmp.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.epam.jmp.dto.GroupDTO;

public class GroupDTOValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return GroupDTO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}
	
}
