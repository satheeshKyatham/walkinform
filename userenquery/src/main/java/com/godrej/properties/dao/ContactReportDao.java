package com.godrej.properties.dao;

import java.util.Map;

import com.godrej.properties.model.ContactReport;

public interface ContactReportDao {
	public ContactReport insertContactReport(ContactReport enq);

	public ContactReport update(ContactReport entity);
	
	public ContactReport findById(Integer id);
	public ContactReport getContactReportById(Integer id);
	
	public void updateContactSfidToCustomContact();
	
	public void updateById(Map<String,Object> params);
	
	public void updateRatingSFDC(ContactReport contcat); 
	public void updateContactONSFDC(ContactReport contcat); 
	public ContactReport getContactReportData(Integer id);
	
}
