package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.ExtraCharges;

public interface ExtraChargesExistsService {

	ExtraCharges getECData(String contactNo);

	void updateExtraCharges(List<ExtraCharges> charges);

}
