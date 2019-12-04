package com.godrej.properties.daoimpl;

import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.AuditLogDao;
import com.godrej.properties.model.AuditLog;

@Repository("auditLogDao")
public class AuditLogDaoImpl extends AbstractDao<Integer, AuditLog> implements AuditLogDao{
	@Override
	public AuditLog insertAuditLog(AuditLog aLog) {
		persist(aLog);
		return aLog;
	}

}
