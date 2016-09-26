package com.epam.jmp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.epam.jmp.model.Person;

@Component
public class PersonValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		Person user = (Person)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "","Personname is empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "level", "", "Level is empty");
		if (user.getName().length()<5) {
			errors.rejectValue("name","", "Personname length is less than 5");
		}
	}
}
