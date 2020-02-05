package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.PrePaymentReceived;

public interface PrePaymentReceivedService {
	
	public List<PrePaymentReceived> getPrePaymentDetails(String whereCondition);

}
