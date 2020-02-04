package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.PrePaymentReceived;

public interface PrePaymentReceivedDao {
	
	public List<PrePaymentReceived> getPrePaymentDetails(String whereCondition);

}
