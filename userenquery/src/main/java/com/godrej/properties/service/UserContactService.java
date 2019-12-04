package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.dto.ContactDto;

public interface UserContactService {

	public ContactDto findMobileNoExist(String countryCode, String mobileNo);

	public ContactDto save(ContactDto contact);
	
	public ContactDto update(ContactDto contact);

	public ContactDto findById(ContactDto contact);
	
	public ContactDto updateBaseInfo(ContactDto src, ContactDto dest);

	public ContactDto updateAddressInfo(ContactDto src, ContactDto dest);

	/*public ContactDto updateOtherInfo(ContactDto src, ContactDto dest);*/

	public List<ContactDto> getContactByPartnerSfid(String sfid);
	
	public List<ContactDto> getContactByEmail(String email);
	
	public String getBrokerContact(String brokeraccount);
	
}
