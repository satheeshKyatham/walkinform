package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.PropPrepaymentReceived;

public interface OfferReceivedPaymentDtlDao {
	List<PropPrepaymentReceived> getOfferReceivedPaymentDtl(String offerSFID);
}