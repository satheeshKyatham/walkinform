package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.CostSheet;

public interface CostSheetDao {

	void setCostSheet(List<CostSheet> costSheets, String equery_id);

}
