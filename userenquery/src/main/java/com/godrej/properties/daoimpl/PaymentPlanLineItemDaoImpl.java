package com.godrej.properties.daoimpl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.PaymentPlanLineItemDao;
import com.godrej.properties.model.PaymentPlanLineItem;

@Repository("paymentPlanLineItemDao")
public class PaymentPlanLineItemDaoImpl extends AbstractDao<Integer, PaymentPlanLineItem> implements PaymentPlanLineItemDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<PaymentPlanLineItem> getpaymentplanlist(String paymentPlan_code_sfid){
		Session session = this.sessionFactory.getCurrentSession();	
		List<PaymentPlanLineItem> list =new ArrayList<>();
		list=session.createQuery("  from  PaymentPlanLineItem where propstrength__payment_plan__c ='"+paymentPlan_code_sfid+"'").list(); //where propstrength__payment_plan__c='"+project_code+"'
		if(list.size()>0)
		{
			
			PaymentPlanLineItem a=list.get(list.size()-1);
			a.setLastRowYN("Y");
			list.set(list.size()-1, a);
			return list;
		}
		return list;
		
	}
	/*Added by satheesh*/
	@Override
	public List<PaymentPlanLineItem> getpaymentplanlist() {
		// TODO Auto-generated method stub
		return null;
	}

}