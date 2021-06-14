package com.godrej.properties.daoimpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CPAPP_EnquiryDao;
import com.godrej.properties.model.CPAPP_EnquiryRequest;


@SuppressWarnings("unchecked")
@Repository("cPAPP_EnquiryDao")
public class CPAPP_EnquiryDaoImpl extends AbstractDao<Integer, CPAPP_EnquiryRequest> implements CPAPP_EnquiryDao {

	@Autowired
	private SessionFactory sessionFactory;
	 

	@Override
	public void updateEnquery_CPAPP(CPAPP_EnquiryRequest cpapp_EnquiryRequest) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE CPAPP_EnquiryRequest SET "
				+ "PropStrength__Broker_Account__c = '"+cpapp_EnquiryRequest.getPropStrength__Broker_Account__c()
				+ "',propstrength__request_source__c = '"+cpapp_EnquiryRequest.getPropstrength__request_source__c()
				+ "',Walk_In_Source__c = '"+cpapp_EnquiryRequest.getWalk_In_Source__c()
				+ "',Date_of_Site_Visit__c = '"+cpapp_EnquiryRequest.getDate_of_Site_Visit__c()
				+"'  WHERE sfid = '"+cpapp_EnquiryRequest.getSfid()+"'");
		
		query.executeUpdate();	
	}

	@Override
	public CPAPP_EnquiryRequest createEnquery_CPAPP(CPAPP_EnquiryRequest cpapp_EnquiryRequest) {
		// TODO Auto-generated method stub
		
		persist(cpapp_EnquiryRequest);
		
		/*
		 * StringBuilder jpql=new StringBuilder();
		 * jpql.append(" SELECT c FROM CPAPP_EnquiryRequest c where c.email=:email");
		 * Map<String, Object> params=new HashMap<>(); params.put("email",
		 * contact.getEmail()); List<Contact> contacts=getEntities(jpql.toString(),
		 * params); if(contacts.size()>0) { return contacts.get(0); } return contact;
		 */
		return cpapp_EnquiryRequest;
	}

	@Override
	public void updateEnquery_status_CPAPP(CPAPP_EnquiryRequest cpapp_EnquiryRequest) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE CPAPP_EnquiryRequest SET "
				+ "PropStrength__Request_Status__c = '"+cpapp_EnquiryRequest.getPropStrength__Request_Status__c()
			 	+"'  WHERE sfid = '"+cpapp_EnquiryRequest.getSfid()+"'");
	
		query.executeUpdate();	
	}

	
}
