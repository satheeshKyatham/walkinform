package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.ReceivedPaymentDtl;

public interface ReceivedPaymentDtlService {
	List<ReceivedPaymentDtl> getReceivedPaymentDtl(String applicationSfid);
}
