package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.OfferReport;
import com.godrej.properties.model.Vw_OfferWithBalance;

public interface VW_OfferWithBalanceDao {
	List<Vw_OfferWithBalance> getOfferListList(String whereCondition);
	
	List<OfferReport> getOfferDtl(String whereCondition);
}