package com.godrej.properties.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.converter.ContactReportConverter;
import com.godrej.properties.dao.ContactReportDao;
import com.godrej.properties.dao.ContactReportLogDao;
import com.godrej.properties.dto.ContactDto;
import com.godrej.properties.dto.ContactReportDto;
import com.godrej.properties.model.ContactReport;
import com.godrej.properties.model.ContactReportLog;
import com.godrej.properties.service.ContactReportService;
@Service
public class ContactReportServiceImpl implements ContactReportService{
    
	@Autowired
	private ContactReportDao ContactReportDao;
	@Autowired
	private ContactReportLogDao ContactReportLogDao;
	@Autowired
	private ContactReportConverter ContactReportConverter; 
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ContactReportDto save(ContactReportDto dto) {
		ContactReport ContactReport=ContactReportConverter.dtoToEntity(dto);
		System.out.println("ContactReportDto query Start :"+new Date());
		ContactReport=ContactReportDao.insertContactReport(ContactReport);
		System.out.println("ContactReportDto query End :"+new Date());
		//Update contact
		
		return ContactReportConverter.entityToDto(ContactReport);
	}

	@Override
	public ContactReportDto findById(ContactReportDto dto) {
		ContactReport ContactReport=ContactReportDao.findById(dto.getContactReportId());
		return ContactReportConverter.entityToDto(ContactReport);
	}

	@Override
	@Transactional
	public ContactReportDto update(ContactReportDto src, ContactReportDto dest) {
		/*ContactReport ContactReport=ContactReportConverter.dtoToEntity(dto);
		ContactReport=ContactReportDao.update(ContactReport);
		return ContactReportConverter.entityToDto(ContactReport);*/
		return null;
	}

	@Override
	@Transactional
	public ContactReportDto update(ContactReportDto dto) {
		ContactReport ContactReport=ContactReportConverter.dtoToEntity(dto);
		ContactReport=ContactReportDao.update(ContactReport);
		ContactReportDao.updateRatingSFDC(ContactReport);
		ContactReportDao.updateContactONSFDC(ContactReport);
		return ContactReportConverter.entityToDto(ContactReport);
	}

	@Override
	@Transactional
	public ContactReportDto updateBaseInfo(ContactReportDto src, ContactReportDto dest) {
		dest.setAgeGroup(src.getAgeGroup());
		dest.setOfficeAddress(src.getOfficeAddress());
		dest.setOfficeCity(src.getOfficeCity());
		dest.setOfficePincode(src.getOfficePincode());
		dest.setEmploymentStatus(src.getEmploymentStatus());
		
		dest.setOfficelat(src.getOfficelat());
		dest.setOfficelng(src.getOfficelng());
		dest.setReslat(src.getReslat());
		dest.setReslng(src.getReslng());

		return update(dest);
	}

	@Override
	@Transactional
	public ContactReportDto updateAddressInfo(ContactReportDto src, ContactReportDto dest) {
		dest.setGender(src.getGender());
		dest.setCustomerClassification(src.getCustomerClassification());
		dest.setEthnicity(src.getEthnicity());
		dest.setCurrentResidenceOwnership(src.getCurrentResidenceOwnership());
		dest.setCurrentResidenceType(src.getCurrentResidenceType());
        dest.setOccupation(src.getOccupation());
        dest.setTokenno(src.getTokenno());
        dest.setUserid(src.getUserid());
        
        ContactReportLog contactLog = new ContactReportLog();
        contactLog.setGender(dest.getGender());
        contactLog.setCustomerClassification(dest.getCustomerClassification());
		contactLog.setEthnicity(dest.getEthnicity());
		contactLog.setCurrentResidenceOwnership(dest.getCurrentResidenceOwnership());
		contactLog.setCurrentResidenceType(dest.getCurrentResidenceType());
		contactLog.setOccupation(dest.getOccupation());
        
		contactLog.setContactReportId(dest.getContactReportId());
		contactLog.setAgeGroup(dest.getAgeGroup());
		contactLog.setOfficeAddress(dest.getOfficeAddress());
		contactLog.setOfficeCity(dest.getOfficeCity());
		contactLog.setOfficePincode(dest.getOfficePincode());
		contactLog.setEmploymentStatus(dest.getEmploymentStatus());
		
		contactLog.setOfficelat(dest.getOfficelat());
		contactLog.setOfficelng(dest.getOfficelng());
		contactLog.setReslat(dest.getReslat());
		contactLog.setReslng(dest.getReslng());
		contactLog.setIsUpdated("Y");
		contactLog.setContactSfid(dest.getContactSfid());
		contactLog.setContactId(dest.getContactId());
		contactLog.setContactName(dest.getContactName());
		contactLog.setMobileNo(dest.getMobileNo());
		
		contactLog.setCreatedDate(new java.sql.Date(System.currentTimeMillis()));
		contactLog.setTokenno(dest.getTokenno());
		contactLog.setUserid(dest.getUserid());
		ContactReportLogDao.insertContactReport(contactLog);
		return update(dest);
	}

	@Override
	public ContactReportDto getContactReportById(ContactReportDto dto) {
		ContactReport contactReport=ContactReportDao.getContactReportById(dto.getContactReportId());
		return ContactReportConverter.entityToDto(contactReport);
	}
	@Override
	@Transactional
	public void updateContactSfidToCustomContact() {
		ContactReportDao.updateContactSfidToCustomContact();		
	}

	@Override
	@Transactional
	public void updateById(ContactDto dto) {
		Map<String,Object> params=new HashMap<>();
		params.put("contactId",dto.getContactId());
		params.put("contactReportId",dto.getContactReport().getContactReportId());
		ContactReportDao.updateById(params);		
	}
}
