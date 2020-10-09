package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.ApplicationDtlDao;
import com.godrej.properties.model.ApplicationDtl;

@Repository("applicationDtlDao")
public class ApplicationDtlDaoImpl implements ApplicationDtlDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ApplicationDtl> getApplicationDtl(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<ApplicationDtl> authors=null;
		
		Query q = session.createNativeQuery("SELECT a.createddate, a.id, a.application_booking_id_18__c, a.propstrength__project__c, a.propstrength__primary_applicant_name__c, "
				+ " a.booking_source__c, a.propstrength__broker_name__c, a.propstrength__property_name__c, a.propstrength__tower__c, a.propstrength__type__c, a.enquiry18digit__c, "
				+ " b.verticle__c "
				+ " FROM salesforce.propstrength__application_booking__c a "
				+ " LEFT JOIN salesforce.propstrength__request__c b ON b.sfid = a.enquiry18digit__c  "
				+ " where "+whereCondition+" and a.booking_status__c != 'Booking_Cancelled' order by a.createddate DESC ", ApplicationDtl.class);

		authors = q.getResultList();
		
		System.out.println("Final Size::"+authors.size());
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}	