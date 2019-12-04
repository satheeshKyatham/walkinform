package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.EOIPaymentDtlDao;
import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.model.ExtraCharges;
import com.godrej.properties.service.EOIPaymentDtlService;

@Service("eOIpaymentDtlService")
@Transactional
public class EOIPaymentDtlServiceImpl implements EOIPaymentDtlService{
	
	@Autowired
	private EOIPaymentDtlDao dao;

	@Override
	public void insertPaymentDtl(List<EOIPaymentDtl> pymtDtl) {
		dao.insertPaymentDtl(pymtDtl);
	}
	
	@Override
	public List<EOIPaymentDtl> getEOIPaymentRecord(String enqSfid) {
		return dao.getEOIPaymentRecord(enqSfid);
	}
	
	@Override
	public void updateEOIForOffer(List<EOIPaymentDtl> charges) {
		  dao.updateEOIForOffer(charges);
	}
}