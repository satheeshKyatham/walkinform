package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.dto.CPEngmntDto;
import com.godrej.properties.model.CPEngmntReport;

public interface CPEngmntService {
	public CPEngmntDto insertAuditLog(CPEngmntDto auditLogDto);
	
	List<CPEngmntReport> getReportDtl(String whereCondition);
}