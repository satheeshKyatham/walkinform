package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.Vw_OfferWithBalance;

public interface VW_OfferWithBalanceService {

	List<Vw_OfferWithBalance> getOfferList(String whereCondition);
}
