package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.ExtraCharges;

public interface ExtraChargesExistsDao {
	ExtraCharges getECData (String contactNo);

	void updateExtraCharges(List<ExtraCharges> charges);
}
