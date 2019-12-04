package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.ReceivedPaymentDtl;

public interface ReceivedPaymentDtlDao {
	List<ReceivedPaymentDtl> getReceivedPaymentDtl(String applicationSfid);
}
