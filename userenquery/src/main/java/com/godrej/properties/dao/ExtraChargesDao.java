package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.ExtraCharges;

public interface ExtraChargesDao {
	
	void setExtraChrgs(List<ExtraCharges> charges, String equery_id, String contactNo);

}
