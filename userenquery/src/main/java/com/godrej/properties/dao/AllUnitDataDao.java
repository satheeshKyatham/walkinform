package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.AllUnitData;

public interface AllUnitDataDao {
	List<AllUnitData>getInventoryReportDtl(String whereCondition);
}