package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.PaymentPlanList;
import com.godrej.properties.model.TnC;

public interface TnCDao {
	void insertTNCForPP (TnC tnC);
	
	List<TnC> getTncData (String ppId, String projectid);
	
	
}
