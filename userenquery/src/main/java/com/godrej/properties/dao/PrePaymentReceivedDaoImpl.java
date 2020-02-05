package com.godrej.properties.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.model.AllotmentReport;
import com.godrej.properties.model.PrePaymentReceived;


@Repository("prePaymentDao")
public class PrePaymentReceivedDaoImpl implements PrePaymentReceivedDao{

	private Logger Log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<PrePaymentReceived> getPrePaymentDetails(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<PrePaymentReceived> paymentRecv=null;
		try
		{
			Query q = session.createQuery(" FROM PrePaymentReceived  where propstrength__offer__c='"+whereCondition+"'");
			paymentRecv = q.getResultList();
			if (paymentRecv.size() > 0)
				return paymentRecv;
			else
				return paymentRecv;
		}
		catch (Exception e) {
			Log.error("Error", e);
			return paymentRecv;
		}
	}

}
