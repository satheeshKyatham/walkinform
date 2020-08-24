package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

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

	
	
	/*@Override
	public List<PaymentPlanList> getPPList(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		List<PaymentPlanList> list =session.createQuery(" from PaymentPlanList where "+whereCondition+" order by name").list();
		
		if(list.size()>0)
			return list;
		return null;
	}*/
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentPlanList> getPPList(String whereCondition){
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<PaymentPlanList> authors =null;
		
		Query q = session.createNativeQuery(" SELECT * FROM salesforce.propstrength__payment_plan__c "
				 
				+ " where  "+whereCondition+" order by name ", PaymentPlanList.class);
			
			authors = (List<PaymentPlanList>)q.getResultList();
			
		if(authors.size()>0)
		{
			return authors;
		}
		return null;
	}
	
	
	
	
	
	
	
	
	@Override
	public void updatePP(PaymentPlanList paymentPlanList) {
		
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		List<PaymentPlanList> list =session.createQuery(" FROM  PaymentPlanList "
				+ " where  propstrength__project__c ='"+paymentPlanList.getPropstrength__project__c()+"' "
				+ " and sfid ='"+paymentPlanList.getSfid()+"'").list();
		
		if(list != null && list.size()>0) {
			Query query = session.createNativeQuery(" update salesforce.propstrength__payment_plan__c set d4u_active__c="+paymentPlanList.getD4u_active__c()
			+ " where propstrength__project__c ='" +paymentPlanList.getPropstrength__project__c()+"' and sfid ='"+paymentPlanList.getSfid()+"'");
			 
			query.executeUpdate();
		}
			 
		
		/*Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery(" FROM  PaymentPlanList where  propstrength__project__c ='"
		+paymentPlanList.getPropstrength__project__c()+"' and sfid ='"+paymentPlanList.getSfid()+"'");
		 
		List<PaymentPlanList>  list1  =(List<PaymentPlanList>)q.list();
		
		if(list1!=null)
		{
			if(list1.size()>0) {
				Query query = session.createNativeQuery(" update salesforce.propstrength__payment_plan__c set d4u_active__c="+paymentPlanList.getD4u_active__c()
				+ " where propstrength__project__c ='" +paymentPlanList.getPropstrength__project__c()+"' and sfid ='"+paymentPlanList.getSfid()+"'");
				query.executeUpdate();
			
			}
		}*/		

	}
	
}
