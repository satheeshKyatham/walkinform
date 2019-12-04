package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.OfferReceivedPaymentDtlDao;
import com.godrej.properties.dao.ReceivedPaymentDtlDao;
import com.godrej.properties.model.OfferReceivedPaymentDtl;
import com.godrej.properties.model.ReceivedPaymentDtl;
import com.godrej.properties.service.OfferReceivedPaymentDtlService;
import com.godrej.properties.service.ReceivedPaymentDtlService;

@Service("offerReceivedPaymentDtlService")
@Transactional
public class OfferReceivedPaymentDtlServiceImpl implements OfferReceivedPaymentDtlService{
	@Autowired
	OfferReceivedPaymentDtlDao offerReceivedPaymentDtlDao;
	
	@Override
	public List<OfferReceivedPaymentDtl> getOfferReceivedPaymentDtl(String offerSFID) {
		return offerReceivedPaymentDtlDao.getOfferReceivedPaymentDtl(offerSFID);
	}
}