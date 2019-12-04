package com.godrej.properties.converter;

import java.util.List;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Component;

import com.godrej.properties.common.converter.CommonConverter;
import com.godrej.properties.dto.ContactReportDto;
import com.godrej.properties.model.ContactReport;

/**
 * 
 * @author Varsha Patil
 *
 */
@Component
public class ContactReportConverter  implements CommonConverter<ContactReport, ContactReportDto>{
	 @Override
		public ContactReportDto entityToDto(ContactReport entity) {
			if(null == entity){
				return null;
			}
			if(entity instanceof HibernateProxy) {
				return null;
			}
			
			ContactReportDto dto=new ContactReportDto();
			/*dto.setAccompaniedBy(entity.getAccompaniedBy());
			dto.setConstructionStatus(entity.getConstructionStatus());*/
			dto.setCustomerClassification(entity.getCustomerClassification());
			/*dto.setDealNegotiation(entity.getDealNegotiation());*/
			dto.setContactId(entity.getContactId());
			dto.setContactSfid(entity.getContactSfid());
			/*if(null!=entity.getContact()){
				dto.setContact(new ContactDto());
				dto.getContact().setContactId(entity.getContact().getContactId());
		    }
			if(null!=entity.getContactSfid()){
				dto.setContactSfid(new ContactDto());
				dto.getContactSfid().setSfid(entity.getContactSfid().getSfid());
				dto.getContactSfid().setContactId(entity.getContactSfid().getContactId());
		    }*/
			dto.setContactReportId(entity.getContactReportId());
			dto.setEthnicity(entity.getEthnicity());
			dto.setGender(entity.getGender());
			
			dto.setCurrentResidenceOwnership(entity.getCurrentResidenceOwnership());
			dto.setCurrentResidenceType(entity.getCurrentResidenceType());
			dto.setOccupation(entity.getOccupation());
			
			dto.setAgeGroup(entity.getAgeGroup());
			dto.setEmploymentStatus(entity.getEmploymentStatus());
			dto.setOfficeAddress(entity.getOfficeAddress());
			dto.setOfficeCity(entity.getOfficeCity());
			dto.setOfficePincode(entity.getOfficePincode());
			dto.setIsUpdated(entity.getIsUpdated());
			/*dto.setTimeframeToBook(entity.getTimeframeToBook());
			dto.setUnitAvailability(entity.getUnitAvailability());
			dto.setVastuPreference(entity.getVastuPreference());*/
			dto.setContactName(entity.getContactName());
			dto.setMobileNo(entity.getMobileNo());
			
			dto.setOfficelat(entity.getOfficelat());
			dto.setOfficelng(entity.getOfficelng());
			dto.setReslat(entity.getReslat());
			dto.setReslng(entity.getReslng());
			dto.setProjectId(entity.getProjectId());
			dto.setTokenno(entity.getTokenno());
			return dto;
		}

		@Override
		public ContactReport dtoToEntity(ContactReportDto dto) {
			if(null == dto){
				return null;
			}
			
			ContactReport contact=new ContactReport();
			/*contact.setAccompaniedBy(dto.getAccompaniedBy());
			contact.setConstructionStatus(dto.getConstructionStatus());*/
			contact.setCustomerClassification(dto.getCustomerClassification());
			/*contact.setDealNegotiation(dto.getDealNegotiation());*/
			/*if(null!=dto.getEnquiry()){
				contact.setEnquiry(new Enquiry());
				contact.getEnquiry().setEnquiryId(dto.getEnquiry().getEnquiryId());
			}*/
			contact.setContactId(dto.getContactId());
			contact.setContactSfid(dto.getContactSfid());
			/*if(null!=dto.getContact()){
				contact.setContact(new Contact());
				contact.getContact().setContactId(dto.getContact().getContactId());
			}
			if(null!=dto.getContactSfid()){
				contact.setContactSfid(new Contact());
				contact.getContactSfid().setSfid(dto.getContactSfid().getSfid());
				contact.getContactSfid().setContactId(dto.getContactSfid().getContactId());
		    }*/
			contact.setContactReportId(dto.getContactReportId());
			contact.setEthnicity(dto.getEthnicity());
			contact.setGender(dto.getGender());
			
			contact.setCurrentResidenceOwnership(dto.getCurrentResidenceOwnership());
			contact.setCurrentResidenceType(dto.getCurrentResidenceType());
			contact.setOccupation(dto.getOccupation());
			
			contact.setAgeGroup(dto.getAgeGroup());
			contact.setEmploymentStatus(dto.getEmploymentStatus());
			contact.setOfficeAddress(dto.getOfficeAddress());
			contact.setOfficeCity(dto.getOfficeCity());
			contact.setOfficePincode(dto.getOfficePincode());
			contact.setIsUpdated(dto.getIsUpdated());
			/*contact.setTimeframeToBook(dto.getTimeframeToBook());
			contact.setUnitAvailability(dto.getUnitAvailability());
			contact.setVastuPreference(dto.getVastuPreference());*/			
			contact.setContactName(dto.getContactName());
			contact.setMobileNo(dto.getMobileNo());
			
			contact.setOfficelat(dto.getOfficelat());
			contact.setOfficelng(dto.getOfficelng());
			contact.setReslat(dto.getReslat());
			contact.setReslng(dto.getReslng());
			contact.setProjectId(dto.getProjectId());
			contact.setTokenno(dto.getTokenno());
			//contact.setOfficelat(dto.getOfficelat());
			return contact;
		}

		@Override
		public List<ContactReportDto> entityToDto(List<ContactReport> entityList) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<ContactReport> dtoToEntity(List<ContactReportDto> dtoList) {
			// TODO Auto-generated method stub
			return null;
		}
}
