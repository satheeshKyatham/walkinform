package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.Scheme;

public interface SchemeChargeService {
	void insertSchemeCharge(Scheme schemeCharges);
	List<Scheme> getSchemeChargs(int schemeSource2, int schemeSite, int schemeSource, String projectid, String cp_flag_yn);
}
