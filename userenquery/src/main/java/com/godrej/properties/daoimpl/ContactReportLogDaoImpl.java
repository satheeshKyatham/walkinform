package com.godrej.properties.daoimpl;

import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AAbstractDao;
import com.godrej.properties.dao.ContactReportLogDao;
import com.godrej.properties.model.ContactReportLog;

@Repository
public class ContactReportLogDaoImpl extends AAbstractDao<ContactReportLog> implements ContactReportLogDao{

	@Override
	public ContactReportLog insertContactReport(ContactReportLog enq) {
		persist(enq);
		return enq;
	}

}
