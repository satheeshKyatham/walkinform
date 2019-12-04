package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.BSPTaxRecordDao;
import com.godrej.properties.model.BSPTaxRecord;
import com.godrej.properties.service.BSPTaxRecordService;

@Service("bSPTaxRecordService")
@Transactional
public class BSPTaxRecordServiceImpl implements BSPTaxRecordService{
	
	@Autowired
	private BSPTaxRecordDao payment;  
	
	@Override
	public List<BSPTaxRecord> getBSPTax() {
		// TODO Auto-generated method stub
		return payment.getBSPTax();
	}
}
