package com.godrej.properties.core.validator;

import com.godrej.properties.core.dto.Errors;

/**
 * Object Validator
 * @author varsha
 *
 */
public interface Validator {

	public void validate(Object object, Errors errors);
}
