package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.OfferReceivedPaymentDtlDao;
import com.godrej.properties.model.PropPrepaymentReceived;
import com.godrej.properties.service.OfferReceivedPaymentDtlService;

@Service("offerReceivedPaymentDtlService")
@Transactional
public class OfferReceivedPaymentDtlServiceImpl implements OfferReceivedPaymentDtlService{
	@Autowired
	OfferReceivedPaymentDtlDao offerReceivedPaymentDtlDao;
	
	@Override
	public List<PropPrepaymentReceived> getOfferReceivedPaymentDtl(String offerSFID) {
		return offerReceivedPaymentDtlDao.getOfferReceivedPaymentDtl(offerSFID);
	}
}