package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.OfferReceivedPaymentDtlDao;
import com.godrej.properties.model.PropPrepaymentReceived;

@Repository("offerReceivedPaymentDtlDao")
public class OfferReceivedPaymentDtlDaoImpl extends AbstractDao<Integer, PropPrepaymentReceived> implements OfferReceivedPaymentDtlDao{
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public List<PropPrepaymentReceived> getOfferReceivedPaymentDtl(String offerSFID) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<PropPrepaymentReceived> authors=null;
		
		//and recordtypeid = '01290000000uHVT'
		
		//Query q = session.createNativeQuery("SELECT gpl_cs_payment_details_id, payment_type, bank_name, transaction_amount, branch, transaction_id FROM salesforce.gpl_cs_payment_details   where  offer_sfid = '"+offerSFID+"'  ", Prepayment.class);
		
		Query q = session.createNativeQuery("SELECT * FROM salesforce.propstrength__prepayment_received__c   where  propstrength__offer__c = '"+offerSFID+"'  ", PropPrepaymentReceived.class);
		
		//order by b.id
		authors = q.getResultList();
		
		System.out.println("Final Size::"+authors.size());
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}
