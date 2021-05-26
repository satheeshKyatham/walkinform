package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.CPEngmnt;
import com.godrej.properties.model.CPEngmntReport;

public interface CPEngmntDao {
	public CPEngmnt insertAuditLog(CPEngmnt aLog);
	List<CPEngmntReport>getReportDtl(String whereCondition);
}