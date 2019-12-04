package com.godrej.properties.service;

import com.godrej.properties.dto.ContactDto;
import com.godrej.properties.dto.ContactReportDto;

public interface ContactReportService {
    public ContactReportDto save(ContactReportDto dto);
	
	public ContactReportDto findById(ContactReportDto dto);
	
	public ContactReportDto getContactReportById(ContactReportDto dto);
	
	public ContactReportDto update(ContactReportDto src,ContactReportDto dest);
	
	public ContactReportDto update(ContactReportDto dto);
	
	public ContactReportDto updateBaseInfo(ContactReportDto src, ContactReportDto dest);

	public ContactReportDto updateAddressInfo(ContactReportDto src, ContactReportDto dest);
	
	public void updateContactSfidToCustomContact();
	
	public void updateById(ContactDto dto);
}
