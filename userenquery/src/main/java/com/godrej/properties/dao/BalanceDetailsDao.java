package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.BalanceDetails;

public interface BalanceDetailsDao {
	BalanceDetails insertBalanceDetails (BalanceDetails action);
	List<BalanceDetails> getCreatedOffersList(String projectid);
	
}
