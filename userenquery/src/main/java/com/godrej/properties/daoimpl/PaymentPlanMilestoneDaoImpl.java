package com.godrej.properties.daoimpl;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.PaymentPlanMilestoneDao;
import com.godrej.properties.model.PaymentPlanMilestone;
import com.godrej.properties.model.Scheme;

@Repository("paymentPlanMilestoneDao")
public class PaymentPlanMilestoneDaoImpl extends AbstractDao<Serializable, PaymentPlanMilestone> implements PaymentPlanMilestoneDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<PaymentPlanMilestone> getPaymentPlanLineItems(String ppSFID) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		/*@SuppressWarnings("unchecked")
		List<PaymentPlanMilestone> list =session.createQuery(" from PaymentPlanMilestone where propstrength__payment_plan__c='"+ppSFID+"' order by name").list();
		if(list.size()>0)
			return list;
		
		return null;*/
		
		
		/*@SuppressWarnings("unchecked")
		List<PaymentPlanMilestone> list =session.createQuery(" SELECT line.propstrength__payment_plan_line_item_name__c AS nv_payment_plan_line_item_name__c, "
				
				+ " CASE "
					+ " WHEN line.propstrength__amount_percent__c IS NULL THEN ''::text "
					+ " ELSE concat('', line.propstrength__amount_percent__c) "
				+ " END AS nv_amount_percent__c, "
				+ " line.propstrength__payment_plan__c, "
				+ " line.id, "
				+ " line.name, "
				+ " line.sfid "
				+ " FROM salesforce.propstrength__payment_plan_line_items__c line "
				+ " JOIN salesforce.propstrength__milestone__c milestone ON line.propstrength__milestone__c::text = milestone.sfid::text "
				+ " WHERE line.propstrength__payment_plan_line_item_name__c::text != '%Dummy%'::text "
					+ "	AND line.propstrength__payment_plan_line_item_name__c::text != '%DUMMY%'::text "
					+ " AND line.propstrength__payment_plan_line_item_name__c::text != '%dummy%'::text "
				+ " AND line.propstrength__payment_plan__c='"+ppSFID+"' order by name").list();
		
		if(list.size()>0)
			return list;
		
		return null;
		*/
		
		
		
		
		//Session session = this.sessionFactory.getCurrentSession();	
		
		List<PaymentPlanMilestone> authors=null;
		
		Query q = session.createNativeQuery(" SELECT line.propstrength__payment_plan_line_item_name__c AS nv_payment_plan_line_item_name__c, "
				
				/*+ " CASE "
					+ " WHEN line.propstrength__amount_percent__c IS NULL THEN '' "
					+ " ELSE concat('', line.propstrength__amount_percent__c) "
				+ " END AS nv_amount_percent__c, "
				*/
				
				+ " CASE "
					+ "	WHEN line.propstrength__amount_percent__c IS NULL THEN cast(0 as numeric (20,2)) "
					+ "	ELSE cast(line.propstrength__amount_percent__c as numeric (20,2))   "
				+ "END AS nv_amount_percent__c , "
				
				
				+ " line.propstrength__payment_plan__c, "
				+ " line.id, "
				+ " line.name, "
				+ " line.sfid "
				+ " FROM salesforce.propstrength__payment_plan_line_items__c line "
				+ " JOIN salesforce.propstrength__milestone__c milestone ON line.propstrength__milestone__c = milestone.sfid "
				
				+ " WHERE line.propstrength__payment_plan_line_item_name__c NOT LIKE '%Dummy%' "
				+ "	AND line.propstrength__payment_plan_line_item_name__c NOT LIKE '%DUMMY%' "
				+ " AND line.propstrength__payment_plan_line_item_name__c NOT LIKE '%dummy%' "
				+ " AND line.propstrength__payment_plan__c='"+ppSFID+"' order by name " , PaymentPlanMilestone.class); 
				
		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
		
		
		
		
	}
}