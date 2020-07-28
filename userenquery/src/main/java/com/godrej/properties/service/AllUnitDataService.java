package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.AllUnitData;

public interface AllUnitDataService {
	List<AllUnitData> getInventoryReportDtl(String whereCondition);
}