package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.ReceivedPaymentDtlDao;
import com.godrej.properties.model.ReceivedPaymentDtl;

@Repository("receivedPaymentDtlDao")
public class ReceivedPaymentDtlDaoImpl extends AbstractDao<Integer, ReceivedPaymentDtl> implements ReceivedPaymentDtlDao{
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public List<ReceivedPaymentDtl> getReceivedPaymentDtl(String applicationSfid) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<ReceivedPaymentDtl> authors=null;
		
		//and recordtypeid = '01290000000uHVT'
		
		Query q = session.createNativeQuery("SELECT propstrength__amount__c, id, recordtypeid, propstrength__payment_type__c, propstrength__bank_name_auto__c, propstrength__crn_no__c, propstrength__cheque_demand_draft_number__c, propstrength__ifsc_rtgs_code__c FROM salesforce.propstrength__received_payment__c   where  propstrength__application_booking__c = '"+applicationSfid+"' and recordtypeid='01290000000uHVTAA2' ", ReceivedPaymentDtl.class);
		//order by b.id
		authors = q.getResultList();
		
		System.out.println("Final Size::"+authors.size());
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}
