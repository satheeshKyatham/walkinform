package com.godrej.properties.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godrej.properties.core.dto.Errors;
import com.godrej.properties.core.validator.Validator;
import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.util.ValidationUtil;
/**
 * 
 * @author Varsha Patil
 *
 */
@Component
public class EnquiryValidator implements Validator{
	
	@Autowired
	private ValidationUtil validator;

	@Override
	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(EnquiryDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}
		EnquiryDto enq=(EnquiryDto) object;
		validateEnquiry(enq,errors);
	}
    public void validateEnquiry(EnquiryDto dto,Errors errors){
    	
    }
}
