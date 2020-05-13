package com.godrej.properties.daoimpl;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CancelEOIDao;
import com.godrej.properties.model.Enquiry;

@SuppressWarnings("unchecked")
@Repository("cancelEOIDao")
public class CancelEOIDaoImpl extends AbstractDao<Integer, Enquiry> implements CancelEOIDao {
	private Logger Log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Boolean updateEOI(String enq_sfid, String project_sfid, String enqid) {
		 
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE Enquiry  "
				+ " SET eoi_enquiry__c = false "
				+ " WHERE enquiryId = "+enqid+" ");
		
		System.out.println("EOI UPDATE :- " + query);
		
		query.executeUpdate();
		
		return true;
		 
	}
}