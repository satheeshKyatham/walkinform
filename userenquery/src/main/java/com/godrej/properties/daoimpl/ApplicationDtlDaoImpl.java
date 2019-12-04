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
		
		Query q = session.createNativeQuery("SELECT createddate, id, application_booking_id_18__c, propstrength__project__c, propstrength__primary_applicant_name__c, booking_source__c, propstrength__broker_name__c, propstrength__property_name__c, propstrength__tower__c, propstrength__type__c, enquiry18digit__c "
				+ " FROM salesforce.propstrength__application_booking__c  where "+whereCondition+" and booking_status__c != 'Booking_Cancelled' order by createddate DESC ", ApplicationDtl.class);

		authors = q.getResultList();
		
		System.out.println("Final Size::"+authors.size());
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}	