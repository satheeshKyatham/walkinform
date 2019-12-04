package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.ReceivedPaymentDtlDao;
import com.godrej.properties.model.ReceivedPaymentDtl;
import com.godrej.properties.service.ReceivedPaymentDtlService;

@Service("receivedPaymentDtlService")
@Transactional
public class ReceivedPaymentDtlServiceImpl implements ReceivedPaymentDtlService{
	@Autowired
	ReceivedPaymentDtlDao receivedPaymentDtlDao;
	
	@Override
	public List<ReceivedPaymentDtl> getReceivedPaymentDtl(String applicationSfid) {
		return receivedPaymentDtlDao.getReceivedPaymentDtl(applicationSfid);
	}
}