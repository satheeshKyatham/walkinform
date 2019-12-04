package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.WithoutOtherChargesPPDao;
import com.godrej.properties.model.PaymentPlanWithOtherCharge;
import com.godrej.properties.model.WithoutOtherChargesPP;

@Repository("withoutOtherChargesPPDao")

public class WithoutOtherChargesPPDaoImpl extends AbstractDao<Integer, WithoutOtherChargesPP> implements WithoutOtherChargesPPDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WithoutOtherChargesPP> getPPData(String paymentPlanSfid){
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<WithoutOtherChargesPP> authors =null;
			
		
		Query q = session.createNativeQuery(" SELECT row_number() OVER () AS row_number, a.id, "
				+ " CASE  WHEN a.propstrength__amount__c IS NULL THEN cast(0 as double precision) ELSE a.propstrength__amount__c  END AS propstrength__amount__c, "
				+ " CASE  WHEN a.propstrength__amount_percent__c IS NULL THEN cast(0 as double precision) ELSE a.propstrength__amount_percent__c  END AS propstrength__amount_percent__c, "
				
				+ " a.propstrength__payment_plan__c, a.sfid, a.propstrength__payment_plan_line_item_name__c, a.name AS paymentplan_name "
				+ " FROM salesforce.propstrength__payment_plan_line_items__c a "
				
				+ " where propstrength__payment_plan__c = '"+paymentPlanSfid+"' and a.propstrength__amount__c > 0 and a.propstrength__amount_percent__c is null ", WithoutOtherChargesPP.class);
			
			authors = (List<WithoutOtherChargesPP>)q.getResultList();
			
			
			System.out.println("Query ::: " + q);
		
		
		if(authors.size()>0)
		{
			return authors;
		}
		return null;
	}
	
	
	
	
}
