package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.ExtraChargesHis;

public interface ExtraChargesHisService {

	void setExtraChrgs(List<ExtraChargesHis> charges, String equery_id, String contactNo);

}
