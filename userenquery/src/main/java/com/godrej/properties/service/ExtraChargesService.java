package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.ExtraCharges;

public interface ExtraChargesService {

	void setExtraChrgs(List<ExtraCharges> charges, String equery_id, String contactNo);

}
