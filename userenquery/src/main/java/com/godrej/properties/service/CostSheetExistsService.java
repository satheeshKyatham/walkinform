package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.CostSheet;

public interface CostSheetExistsService {

	CostSheet getCSData(String contactNo);
	void updateCostSheet(List<CostSheet> charges);
}
