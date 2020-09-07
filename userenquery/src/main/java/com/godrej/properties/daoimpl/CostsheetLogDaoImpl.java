package com.godrej.properties.daoimpl;

import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CostsheetLogDao;
import com.godrej.properties.model.CostsheetLog;

@Repository("costsheetLogDao")
public class CostsheetLogDaoImpl extends AbstractDao<Integer, CostsheetLog> implements CostsheetLogDao{
	@Override
	public CostsheetLog insertAuditLog(CostsheetLog aLog) {
		persist(aLog);
		return aLog;
	}
}