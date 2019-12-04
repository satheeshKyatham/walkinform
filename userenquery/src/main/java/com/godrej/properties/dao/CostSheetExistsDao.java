package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.CostSheet;

public interface CostSheetExistsDao {

	CostSheet getCSData (String contactNo);
	
	void updateCostSheet(List<CostSheet> charges);
}
