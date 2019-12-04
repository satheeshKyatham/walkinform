package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.BillingData;

public interface BillingViewService {

	List<BillingData> getdataMapping(String page, String count, String str_date);

	String getdataCount(String model, String str_date);

	List<BillingData> getCreateTemp();

	

}
