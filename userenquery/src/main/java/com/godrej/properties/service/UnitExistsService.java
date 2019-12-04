package com.godrej.properties.service;

import com.godrej.properties.model.UnitDtl;

public interface UnitExistsService {

	UnitDtl getUnitData(String contactNo);
	
	UnitDtl unitExist (String unitNo);
	

	void updateUnitDtl(UnitDtl uDtl);

}
