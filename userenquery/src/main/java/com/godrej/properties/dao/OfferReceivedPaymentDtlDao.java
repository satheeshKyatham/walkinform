package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.OfferReceivedPaymentDtl;

public interface OfferReceivedPaymentDtlDao {
	List<OfferReceivedPaymentDtl> getOfferReceivedPaymentDtl(String offerSFID);
}
