package com.epam.jmp.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.epam.jmp.dto.PersonDTO;
import com.epam.jmp.service.PersonService;

@Component
public class PersonDTOValidator implements Validator {
	@Autowired
	private PersonService personService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PersonDTO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "level", "", "Level is empty");
		PersonDTO person = (PersonDTO) target;
		if (personService.isPersonExist(person.getEmail())) {
			errors.rejectValue("email", "", "Email already exist");
		}
		if (person.getManagerDTO() != null && StringUtils.isNotBlank(person.getManagerDTO().getUid())
				&& !personService.isValidUid(person.getManagerDTO().getUid())) {
			errors.rejectValue("managerDTO", "", "Please enter valid Manager UID");
		}
	}
}
