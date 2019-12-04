package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.BillingData;

public interface BillingViewDao {

	List<BillingData> getdataMapping(String page, String count, String str_date);

	String getdataCount(String model, String str_date);

	List<BillingData> getCreateTemp();

}
