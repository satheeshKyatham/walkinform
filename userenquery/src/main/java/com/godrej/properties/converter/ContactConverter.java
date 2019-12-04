package com.godrej.properties.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godrej.properties.common.converter.CommonConverter;
import com.godrej.properties.dto.ContactDto;
import com.godrej.properties.model.Contact;

@Component
public class ContactConverter implements CommonConverter<Contact, ContactDto> {

	@Autowired
	private ChannelPartnerConverter channelPartnerConverter;
	@Autowired
	private ContactReportConverter contactReportConverter;
	@Override
	public ContactDto entityToDto(Contact entity) {
		if(null==entity){
			return null;
		}
		if(entity instanceof HibernateProxy) {
			return null;
		}
		ContactDto dto=new ContactDto();
		dto.setAddressLine1(entity.getAddressLine1());
		dto.setAddressLine2(entity.getAddressLine2());
		dto.setAddressLine3(entity.getAddressLine3());
		dto.setCity(entity.getCity());
		dto.setCompanyLocality(entity.getCompanyLocality());
		dto.setCompanyName(entity.getCompanyName());
		dto.setContactId(entity.getContactId());
		/*dto.setCountry(entity.getCountry());*/
		dto.setDateOfBirth(entity.getDateOfBirth());
		dto.setDesignation(entity.getDesignation());
		dto.setEmail(entity.getEmail());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		/*dto.setLocality(entity.getLocality());*/
		dto.setMobileNo(entity.getMobileNo());
		dto.setOccupation(entity.getOccupation());
		dto.setOfficeCity(entity.getOfficeCity());
		dto.setOfficePinCode(entity.getOfficePinCode());
		dto.setPinCode(entity.getPinCode());
		dto.setSalutation(entity.getSalutation());
		dto.setSfid(entity.getSfid());
		dto.setCurrentOwnershipType(entity.getCurrentOwnershipType());
		dto.setCurrentResidenceType(entity.getCurrentResidenceType());
		dto.setChannelPartner(channelPartnerConverter.entityToDto(entity.getChannelPartner()));
		dto.setAgeGroup(entity.getAgeGroup());
		dto.setResidentialState(entity.getResidentialState());
		dto.setResidentialCountry(entity.getResidentialCountry());
		/*dto.setIndustry(entity.getIndustry());*/
		dto.setCountryCode(entity.getCountryCode());
		if(null!=entity.getContactReport()){
			dto.setContactReport(contactReportConverter.entityToDto(entity.getContactReport()));
		}
		dto.setMobile(entity.getMobile());
		dto.setRecordType(entity.getRecordType());
		
		// If Other Email ID is Null then Set Email ID		 
		if(null==entity.getOtherEmail()) {
			dto.setOtherEmail(entity.getEmail());
		}else {
			dto.setOtherEmail(entity.getOtherEmail());
		}
	 
	    dto.setResidenceFullAddress(entity.getResidenceFullAddress());
	    dto.setMailingCountry(entity.getMailingCountry());
		return dto;
	}

	@Override
	public Contact dtoToEntity(ContactDto dto) {
		if(null==dto){
			return null;
		}
		Contact entity=new Contact();
		entity.setAddressLine1(dto.getAddressLine1());
		entity.setAddressLine2(dto.getAddressLine2());
		entity.setAddressLine3(dto.getAddressLine3());
		entity.setCity(dto.getCity());
		entity.setCompanyLocality(dto.getCompanyLocality());
		entity.setCompanyName(dto.getCompanyName());
		entity.setContactId(dto.getContactId());
		/*entity.setCountry(dto.getCountry());*/
		entity.setDateOfBirth(dto.getDateOfBirth());
		entity.setDesignation(dto.getDesignation());
		
		//Set Other Email Id into email Id
		entity.setOtherEmail(dto.getOtherEmail());
		entity.setEmail(dto.getEmail());
		
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		/*entity.setLocality(dto.getLocality());*/
		entity.setMobileNo(dto.getMobileNo());
		entity.setOccupation(dto.getOccupation());
		entity.setOfficeCity(dto.getOfficeCity());
		entity.setOfficePinCode(dto.getOfficePinCode());
		entity.setPinCode(dto.getPinCode());
		entity.setSalutation(dto.getSalutation());
		entity.setSfid(dto.getSfid());
		entity.setCurrentOwnershipType(dto.getCurrentOwnershipType());
		entity.setCurrentResidenceType(dto.getCurrentResidenceType());
		
		entity.setChannelPartner(channelPartnerConverter.dtoToEntity(dto.getChannelPartner()));
		entity.setAgeGroup(dto.getAgeGroup());
		entity.setResidentialState(dto.getResidentialState());
		entity.setResidentialCountry(dto.getResidentialCountry());
		/*entity.setIndustry(dto.getIndustry());*/
		entity.setCountryCode(dto.getCountryCode());
		if(null!=dto.getContactReport()){
			entity.setContactReport(contactReportConverter.dtoToEntity(dto.getContactReport()));
		}
		entity.setMobile(dto.getMobile());
		entity.setRecordType(dto.getRecordType());
		
	
		
		entity.setResidenceFullAddress(dto.getResidenceFullAddress());
		entity.setMailingCountry(dto.getMailingCountry());
		
		return entity;
	}

	@Override
	public List<ContactDto> entityToDto(List<Contact> entityList) {
		if(null==entityList){
			return Collections.emptyList();
		}
		List<ContactDto> dtoList=new ArrayList<>();
		for(Contact entity:entityList){
			ContactDto dto=entityToDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public List<Contact> dtoToEntity(List<ContactDto> dtoList) {
		if(null==dtoList){
			return Collections.emptyList();
		}
		List<Contact> entityList=new ArrayList<>();
		for(ContactDto dto:dtoList){
			Contact entity=dtoToEntity(dto);
			entityList.add(entity);
		}
		return entityList;
	}

}
