package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.EOIReportDao;
import com.godrej.properties.model.EOIReport;

@Repository("eOIReportDao")
public class EOIReportDaoImpl implements EOIReportDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<EOIReport> getEOIReportDtl(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<EOIReport> authors=null;
		
		Query q = session.createNativeQuery(" SELECT * FROM salesforce.vw_eoi_report "
				+ " where "+whereCondition+"  ", EOIReport.class);
		
		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}	