package com.godrej.properties.dao;

import com.godrej.properties.model.UnitDtl;

public interface UnitExistsDao {

	UnitDtl getUnitData (String contactNo);
	
	UnitDtl unitExist (String unitNo);

	void updateUnitDtl(UnitDtl uDtl);

}
