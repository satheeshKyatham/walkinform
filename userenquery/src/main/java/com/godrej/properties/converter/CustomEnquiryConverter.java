package com.godrej.properties.converter;

import java.util.List;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Component;

import com.godrej.properties.common.converter.CommonConverter;
import com.godrej.properties.dto.ContactDto;
import com.godrej.properties.dto.CustomEnquiryDto;
import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.dto.EnquiryReportDto;
import com.godrej.properties.model.Contact;
import com.godrej.properties.model.CustomEnquiry;
import com.godrej.properties.model.Enquiry;
import com.godrej.properties.model.EnquiryReport;

@Component
public class CustomEnquiryConverter implements CommonConverter<CustomEnquiry, CustomEnquiryDto>{

	@Override
	public CustomEnquiryDto entityToDto(CustomEnquiry entity) {
		if(null == entity){
			return null;
		}
		if(entity instanceof HibernateProxy) {
			return null;
		}
		CustomEnquiryDto dto=new CustomEnquiryDto();
		if(null!=entity.getContact()){
			dto.setContact(new ContactDto());
			dto.getContact().setContactId(entity.getContact().getContactId());
		}		
		if(null!=entity.getEnquiryReport()){
			dto.setEnquiryReport(new EnquiryReportDto());
			dto.getEnquiryReport().setEnquiryReportId(entity.getEnquiryReport().getEnquiryReportId());
		}
		if(null!=entity.getEnquiry()){
			dto.setEnquiry(new EnquiryDto());
			dto.getEnquiry().setEnquiryId(entity.getEnquiry().getEnquiryId());
		}
		dto.setCustomEnquiryId(entity.getCustomEnquiryId());
		dto.setIsReferredByChannelPartnerFlag(entity.getIsReferredByChannelPartnerFlag());
		dto.setSynchronised(entity.getSynchronised());
		return dto;
	}

	@Override
	public CustomEnquiry dtoToEntity(CustomEnquiryDto dto) {
		if(null == dto){
			return null;
		}
		
		CustomEnquiry enquiry=new CustomEnquiry();
		if(null!=dto.getContact()){
			enquiry.setContact(new Contact());
			enquiry.getContact().setContactId(dto.getContact().getContactId());
		}		
		if(null!=dto.getEnquiryReport()){
			enquiry.setEnquiryReport(new EnquiryReport());
			enquiry.getEnquiryReport().setEnquiryReportId(dto.getEnquiryReport().getEnquiryReportId());
		}
		if(null!=dto.getEnquiry()){
			enquiry.setEnquiry(new Enquiry());
			enquiry.getEnquiry().setEnquiryId(dto.getEnquiry().getEnquiryId());
		}
		enquiry.setCustomEnquiryId(dto.getCustomEnquiryId());
		enquiry.setIsReferredByChannelPartnerFlag(dto.getIsReferredByChannelPartnerFlag());
		enquiry.setSynchronised(dto.getSynchronised());
		return enquiry;
	}

	@Override
	public List<CustomEnquiryDto> entityToDto(List<CustomEnquiry> entityList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomEnquiry> dtoToEntity(List<CustomEnquiryDto> dtoList) {
		// TODO Auto-generated method stub
		return null;
	}

}
