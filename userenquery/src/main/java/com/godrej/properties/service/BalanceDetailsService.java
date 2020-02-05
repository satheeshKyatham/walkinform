package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.BalanceDetails;
import com.godrej.properties.model.HoldInventoryEntry;

public interface BalanceDetailsService {
	BalanceDetails insertBalanceDetails (BalanceDetails action);
	List<BalanceDetails> getCreatedOffersList(String projectid);
	void eCRMCancelledOfferInactive();
}
