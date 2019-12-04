package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.ExtraCharges;
import com.godrej.properties.model.ExtraChargesHis;

public interface ExtraChargesHisDao {
	
	void setExtraChrgs(List<ExtraChargesHis> charges, String equery_id, String contactNo);

}
