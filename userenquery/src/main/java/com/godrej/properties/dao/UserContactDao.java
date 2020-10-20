package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.dto.ContactDto;

public interface UserContactDao {

	public ContactDto save(ContactDto s);
	
	public ContactDto update(ContactDto s);

	public ContactDto findById(Integer id);

	public ContactDto findMobileNoExist(String countryCode,String mobileNo);

	public List<ContactDto> getContactByPartnerSfid(String sfid);
	
	public List<ContactDto> getContactByEmail(String email);
	
	public String getBrokerContact(String brokeraccount);
	public int getContactPKID(String contactsfid);
	
	public ContactDto getContactBySfid(String sfid);

}
