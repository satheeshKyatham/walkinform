package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.OTPAdminLogDao;
import com.godrej.properties.model.OTPAdminLog;

@Repository("oTPAdminLogDao")
public class OTPAdminLogDaoImpl extends AbstractDao<Integer, OTPAdminLog> implements OTPAdminLogDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean insertAdminOTPLog(List<OTPAdminLog> log) {
		batchPersist(log);
		return true;
	}
}