package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.EOIDataDao;
import com.godrej.properties.model.EOIRecords;

@Repository("eOIDataDao")
public class EOIDataDaoImpl implements EOIDataDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<EOIRecords> getEOIDtl(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<EOIRecords> authors=null;
		
		Query q = session.createNativeQuery("SELECT sfid, id, priority_no__c"
				+ " FROM salesforce.propstrength__request__c  where "+whereCondition+" ", EOIRecords.class);

		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}