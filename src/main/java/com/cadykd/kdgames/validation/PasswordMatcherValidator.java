package com.cadykd.kdgames.validation;

import com.cadykd.kdgames.dto.RyzomCharacterDTO;
import com.cadykd.kdgames.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatcherValidator implements ConstraintValidator<PasswordMatcher, Object> {
	
	@Override
	public void initialize(PasswordMatcher constraintAnnotation) {
		// nothing needed here
	}
	
	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context){
		UserDTO user = (UserDTO) obj;
		return user.getPassword().equals(user.getMatchingPassword());
	}
}
