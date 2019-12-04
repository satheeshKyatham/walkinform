package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.EOIUpdateDao;
import com.godrej.properties.model.Enquiry;

@SuppressWarnings("unchecked")
@Repository("eOIUpdateDao")
public class EOIUpdateDaoImpl extends AbstractDao<Integer, Enquiry> implements EOIUpdateDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void updateEOI(List<Enquiry> eoiRec) {
		
		for (int i = 0; i <eoiRec.size(); i++) {
			
			Session session = this.sessionFactory.getCurrentSession();
			
			Query query = session.createQuery("UPDATE Enquiry SET priority_no__c = '"+eoiRec.get(i).getPriority_no__c()+"' WHERE id = "+eoiRec.get(i).getEnquiryId()+" ");
			
			query.executeUpdate();
		}
	}
}
