package com.godrej.properties.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
@Converter(autoApply = true)
//@Converter
public class DecimalConverter implements AttributeConverter<Double, Integer>{

	@Override
	public Integer convertToDatabaseColumn(Double arg0) {
		if (arg0 == null) {
	        return 0;
	    } else {
	        return arg0.intValue();
	    }
	}

	@Override
	public Double convertToEntityAttribute(Integer arg0) {
		if (arg0 == null) {
	        return 0.0;
	    } else {
	        return new Double(arg0);
	    }
	}

}
