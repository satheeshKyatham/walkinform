package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.CostSheetHis;

public interface CostSheetHisService {

	void setCostSheet(List<CostSheetHis> costSheetsHis, String equery_id);

}
