package com.godrej.properties.service;

import com.godrej.properties.dto.CustomEnquiryDto;

/**
 * 
 * @author Varsha Patil
 *
 */
public interface CustomEnquiryService {
	public CustomEnquiryDto save(CustomEnquiryDto dto);
	public CustomEnquiryDto update(CustomEnquiryDto dto);
	public CustomEnquiryDto findById(CustomEnquiryDto dto);
	public CustomEnquiryDto saveCustomEnquiry(CustomEnquiryDto dto);
}
