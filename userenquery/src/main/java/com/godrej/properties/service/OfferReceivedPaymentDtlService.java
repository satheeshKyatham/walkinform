package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.PropPrepaymentReceived;

public interface OfferReceivedPaymentDtlService {
	List<PropPrepaymentReceived> getOfferReceivedPaymentDtl(String offerSFID);
}
