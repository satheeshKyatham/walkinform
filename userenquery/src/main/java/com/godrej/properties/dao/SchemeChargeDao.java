package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.Scheme;

public interface SchemeChargeDao {
	void insertSchemeCharge(Scheme schemeCharges);
	
	List<Scheme> getSchemeChargs(int schemeSource, int schemeSite, int schemePromotional, String projectId, String cp_flag_yn);

}
