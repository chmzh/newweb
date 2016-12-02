package com.cmz.web1.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cmz.web1.domain.PersonForm;

public class PersonValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return PersonForm.class.equals(clazz);  
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", null, "Username is empty."); 
		errors.rejectValue("age", null, "age is empty.");
	}

}
