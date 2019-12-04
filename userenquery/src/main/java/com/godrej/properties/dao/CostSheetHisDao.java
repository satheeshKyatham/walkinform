package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.CostSheetHis;

public interface CostSheetHisDao {

	void setCostSheet(List<CostSheetHis> costSheetsHis, String equery_id);

}
