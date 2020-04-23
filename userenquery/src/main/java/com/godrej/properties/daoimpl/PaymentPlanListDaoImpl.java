package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.PaymentPlanListDao;
import com.godrej.properties.model.PaymentPlanList;

@Repository("paymentPlanListDao")
public class PaymentPlanListDaoImpl extends AbstractDao<Integer, PaymentPlanList> implements PaymentPlanListDao{
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	
	@Override
	public List<PaymentPlanList> getPaymentPlanList(String projectCode) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		List<PaymentPlanList> list =session.createQuery(" from PaymentPlanList where PropStrength__Active__c='t' and propstrength__project__c='"+projectCode+"' and d4u_active__c='t' order by name").list();
		
		if(list.size()>0)
			return list;
		return null;
	}


	@Override
	public List<PaymentPlanList> getpaymentPlanWithCIPActiveQuery(String projectId) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		List<PaymentPlanList> list =session.createQuery(" from PaymentPlanList where PropStrength__Active__c='t' and "
				+ "propstrength__project__c='"+projectId+"' and d4u_active__c='t' or cip_payment_plan__c='t' order by name").list();
		
		if(list.size()>0)
			return list;
		return null;
	}

}
