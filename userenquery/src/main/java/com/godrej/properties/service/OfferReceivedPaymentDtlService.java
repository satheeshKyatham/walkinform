package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.OfferReceivedPaymentDtl;

public interface OfferReceivedPaymentDtlService {
	List<OfferReceivedPaymentDtl> getOfferReceivedPaymentDtl(String offerSFID);
}
