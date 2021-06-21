package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.CPAPP_EnquiryDao;
import com.godrej.properties.dao.CarParkChargesDao;
import com.godrej.properties.dao.UserContactDao;
import com.godrej.properties.dto.CPContactEnquiryCreateUpdateReqDto;
import com.godrej.properties.dto.CPEOIRespAPIDto;
import com.godrej.properties.model.CPAPP_EnquiryRequest;
import com.godrej.properties.model.CarParkCharges;
import com.godrej.properties.model.Contact;
import com.godrej.properties.service.CPAPP_Service;
import com.godrej.properties.service.CarParkChargesService;

@Service("cPAPP_Service")
@Transactional
public class CPAPP_ServiceImpl implements CPAPP_Service{
	
	@Autowired
	private UserContactDao contactDao;
	
	@Autowired
	private CPAPP_EnquiryDao cPAPP_EnquiryDao ;
	
	

	@Override
	public Contact createContact(Contact contact) {
		// TODO Auto-generated method stub
		return contactDao.createContact(contact);
	}

	@Override
	public void updateEnquery_CPAPP(CPAPP_EnquiryRequest cpapp_EnquiryRequest) {
		// TODO Auto-generated method stub
		cPAPP_EnquiryDao.updateEnquery_CPAPP(cpapp_EnquiryRequest);
	}

	@Override
	public CPAPP_EnquiryRequest createEnquery_CPAPP(CPAPP_EnquiryRequest cpapp_EnquiryRequest) {
		// TODO Auto-generated method stub
		return cPAPP_EnquiryDao.createEnquery_CPAPP(cpapp_EnquiryRequest);
	}

	@Override
	public void updateEnquery_status_CPAPP(CPAPP_EnquiryRequest cpapp_EnquiryRequest) {
		// TODO Auto-generated method stub
		cPAPP_EnquiryDao.updateEnquery_status_CPAPP(cpapp_EnquiryRequest);
	}

	@Override
	public List<CPEOIRespAPIDto> getPastEOIData(CPContactEnquiryCreateUpdateReqDto cpContactEnquiryCreateUpdateReqDto) {
		// TODO Auto-generated method stub
		return null;
	}
	 
	
	  
	
}
