package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.PaymentPlanWithOtherChargeDao;
import com.godrej.properties.model.Inventory;
import com.godrej.properties.model.PaymentPlanWithOtherCharge;
import com.godrej.properties.model.PropOtherCharges;

@Repository("paymentPlanWithOtherChargeDao")

public class PaymentPlanWithOtherChargeDaoImpl extends AbstractDao<Integer, PaymentPlanWithOtherCharge> implements PaymentPlanWithOtherChargeDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentPlanWithOtherCharge> getppAndOtherCharges(String unitSfid, String paymentPlanSfid,String projectid){
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<PaymentPlanWithOtherCharge> authors =null;
		
		String conditionString = "";
		
		if (projectid.equals("a1l2s00000000X5AAI") || projectid.equals("a1l6F000009D6IMQA0")  || projectid.equals("a1l2s00000003lPAAQ") ) {
			conditionString = " and b1.propstrength__part_of_cop__c = 't' ";
		} else {
			conditionString = " ";
		}
		
		
		/*if(projectid.equals("a1l6F0000080irTQAQ"))
		{
			Query q = session.createNativeQuery("SELECT row_number() OVER () AS row_no, a.*, b.sum,COALESCE(c.propstrength__fixed_charge__c, cast(0 as double precision)) AS propstrength__fixed_charge__c,c.propstrength__type__c AS other_charge_type,CASE WHEN c.propstrength__rate_per_unit_area__c IS NULL THEN cast(0 as double precision) ELSE c.propstrength__rate_per_unit_area__c END AS propstrength__rate_per_unit_area__c,c.propstrength__property__c FROM salesforce.vw_payment_plan_and_other_charges_test a "
					+ " LEFT JOIN salesforce.propstrength__property_charges__c c ON a.propstrength__other_charges__c= c.propstrength__other_charges__c " + 
					" LEFT JOIN salesforce.vw_prop_other_charges b on a.propstrength__other_charges__c = b.propstrength__other_charges__c " + 
					" where   c.propstrength__property__c = '"+unitSfid+"' and b.propstrength__property__c = '"+unitSfid+"' and a.propstrength__payment_plan__c = '"+paymentPlanSfid+"' and b.propstrength__part_of_cop__c='t' order by a.paymentplan_name " , PaymentPlanWithOtherCharge.class);
			authors = (List<PaymentPlanWithOtherCharge>)q.getResultList();
		}
		else
		{*/
			
			//Old Query before Dynamic OLD or New GST
			
		/*Query q = session.createNativeQuery("SELECT row_number() OVER () AS row_no, a.*, b.sum,COALESCE(c.propstrength__fixed_charge__c, cast(0 as double precision)) AS propstrength__fixed_charge__c,c.propstrength__type__c AS other_charge_type,CASE WHEN c.propstrength__rate_per_unit_area__c IS NULL THEN cast(0 as double precision) ELSE c.propstrength__rate_per_unit_area__c END AS propstrength__rate_per_unit_area__c,c.propstrength__property__c FROM salesforce.vw_payment_plan_and_other_charges_test a "
					+ "  LEFT JOIN salesforce.propstrength__property_charges__c c ON a.propstrength__other_charges__c= c.propstrength__other_charges__c " + 
					" LEFT JOIN salesforce.vw_prop_other_charges b on a.propstrength__other_charges__c = b.propstrength__other_charges__c " + 
					" where   c.propstrength__property__c = '"+unitSfid+"' and b.propstrength__property__c = '"+unitSfid+"' and a.propstrength__payment_plan__c = '"+paymentPlanSfid+"' order by a.paymentplan_name " , PaymentPlanWithOtherCharge.class);
			authors = (List<PaymentPlanWithOtherCharge>)q.getResultList();*/
			
			
			/*Query q = session.createNativeQuery("SELECT row_number() OVER () AS row_no, a.*, d.propstrength__completion_certificate_received__c, b.sum,COALESCE(c.propstrength__fixed_charge__c, cast(0 as double precision)) AS propstrength__fixed_charge__c,c.propstrength__type__c AS other_charge_type,CASE WHEN c.propstrength__rate_per_unit_area__c IS NULL THEN cast(0 as double precision) ELSE c.propstrength__rate_per_unit_area__c END AS propstrength__rate_per_unit_area__c,c.propstrength__property__c, d.propstrength__pmay_abatement__c, e.sfid as tower_sfid, e.propstrength__gst_status__c, "
					+ " CASE  WHEN b.propstrength__pmay_gst__c IS NULL THEN cast(0 as double precision)   			ELSE b.propstrength__pmay_gst__c  END AS propstrength__pmay_gst__c, "
					+ " CASE  WHEN b.propstrength__new_pmay_gst__c IS NULL THEN cast(0 as double precision)   		ELSE b.propstrength__new_pmay_gst__c  END AS propstrength__new_pmay_gst__c,"
					+ " CASE  WHEN b.propstrength__tax_percentage__c IS NULL THEN cast(0 as double precision)   	ELSE b.propstrength__tax_percentage__c  END AS propstrength__tax_percentage__c,"
					+ " CASE  WHEN b.propstrength__new_tax_percentage__c IS NULL THEN cast(0 as double precision)  	ELSE b.propstrength__new_tax_percentage__c  END AS propstrength__new_tax_percentage__c, "
					+ " b.propstrength__other_charge_code__c as other_charges_name, "
					+ " b.propstrength__st_on_completion_certificate__c, "
					+ " b.propstrength__sta_applicable__c "
					+ " FROM salesforce.vw_payment_plan_and_other_charges_test a "
					+ " LEFT JOIN salesforce.propstrength__property_charges__c c ON a.propstrength__other_charges__c = c.propstrength__other_charges__c " 
					+ " LEFT JOIN salesforce.vw_prop_other_charges b on a.propstrength__other_charges__c = b.propstrength__other_charges__c "
					+ " Left Join salesforce.propstrength__property__c d on c.propstrength__property__c = d.sfid"
					+ " Left Join salesforce.propstrength__tower__c e on d.propstrength__tower__c = e.sfid" 
					+ " where   c.propstrength__property__c = '"+unitSfid+"' and b.propstrength__property__c = '"+unitSfid+"' and a.propstrength__payment_plan__c = '"+paymentPlanSfid+"' order by a.paymentplan_name " , PaymentPlanWithOtherCharge.class);
			authors = (List<PaymentPlanWithOtherCharge>)q.getResultList();
			*/
		
		
		/* 20190919 */
		/*Query q = session.createNativeQuery("SELECT row_number() OVER () AS row_no, a.*, d.propstrength__completion_certificate_received__c, b.sum,COALESCE(c.propstrength__fixed_charge__c, cast(0 as double precision)) AS propstrength__fixed_charge__c,c.propstrength__type__c AS other_charge_type,CASE WHEN c.propstrength__rate_per_unit_area__c IS NULL THEN cast(0 as double precision) ELSE c.propstrength__rate_per_unit_area__c END AS propstrength__rate_per_unit_area__c,c.propstrength__property__c, d.propstrength__pmay_abatement__c, e.sfid as tower_sfid, e.propstrength__gst_status__c, "
				+ " CASE  WHEN b.propstrength__pmay_gst__c IS NULL THEN cast(0 as double precision)   			ELSE b.propstrength__pmay_gst__c  END AS propstrength__pmay_gst__c, "
				+ " CASE  WHEN b.propstrength__new_pmay_gst__c IS NULL THEN cast(0 as double precision)   		ELSE b.propstrength__new_pmay_gst__c  END AS propstrength__new_pmay_gst__c,"
				+ " CASE  WHEN b.propstrength__tax_percentage__c IS NULL THEN cast(0 as double precision)   	ELSE b.propstrength__tax_percentage__c  END AS propstrength__tax_percentage__c,"
				+ " CASE  WHEN b.propstrength__new_tax_percentage__c IS NULL THEN cast(0 as double precision)  	ELSE b.propstrength__new_tax_percentage__c  END AS propstrength__new_tax_percentage__c, "
				+ " b.propstrength__other_charge_code__c as other_charges_name, "
				+ " b.propstrength__st_on_completion_certificate__c, "
				+ " b.propstrength__sta_applicable__c "
				+ " FROM salesforce.vw_payment_plan_and_other_charges_test a "
				+ " LEFT JOIN salesforce.propstrength__property_charges__c c ON CAST(a.propstrength__other_charges__c as text) = CAST(c.propstrength__other_charges__c as text) " 
				+ " LEFT JOIN salesforce.vw_prop_other_charges b on CAST(a.propstrength__other_charges__c as text) = CAST(b.propstrength__other_charges__c as text) "
				+ " Left Join salesforce.propstrength__property__c d on CAST(c.propstrength__property__c as text)  = CAST(d.sfid as text) "
				+ " Left Join salesforce.propstrength__tower__c e on CAST(d.propstrength__tower__c as text)  = CAST(e.sfid as text) " 
				+ " where   c.propstrength__property__c = '"+unitSfid+"' and b.propstrength__property__c = '"+unitSfid+"' and a.propstrength__payment_plan__c = '"+paymentPlanSfid+"' order by a.paymentplan_name " , PaymentPlanWithOtherCharge.class);
		authors = (List<PaymentPlanWithOtherCharge>)q.getResultList();*/
		
		
		Query q = session.createNativeQuery("SELECT row_number() OVER () AS row_no, d.propstrength__completion_certificate_received__c, "
				
				+ " COALESCE(c.propstrength__fixed_charge__c, cast(0 as double precision)) AS propstrength__fixed_charge__c,c.propstrength__type__c AS other_charge_type,CASE WHEN c.propstrength__rate_per_unit_area__c IS NULL THEN cast(0 as double precision) ELSE c.propstrength__rate_per_unit_area__c END AS propstrength__rate_per_unit_area__c,c.propstrength__property__c, d.propstrength__pmay_abatement__c, e.sfid as tower_sfid, e.propstrength__gst_status__c, "
				
				
				+ " CASE WHEN CAST(b4.propstrength__tax_percentage__c as text) <> CAST('' as text) THEN b4.propstrength__tax_percentage__c * cast(2 as double precision)  ELSE cast(0 as double precision) END AS sum, "  
				
				+ " CASE WHEN CAST(b4.propstrength__pmay_gst__c as text) <> CAST('' as text) THEN b4.propstrength__pmay_gst__c * cast(2 as double precision)  ELSE cast(0 as double precision) END AS propstrength__pmay_gst__c, "
				
				
				+ " CASE WHEN CAST(b4.propstrength__new_pmay_gst__c as text) <> CAST('' as text) THEN b4.propstrength__new_pmay_gst__c * cast(2 as double precision)  ELSE cast(0 as double precision) END AS propstrength__new_pmay_gst__c, "
				
				
				
				+ " CASE WHEN CAST(b4.propstrength__tax_percentage__c as text) <> CAST('' as text) THEN b4.propstrength__tax_percentage__c * cast(2 as double precision)  ELSE cast(0 as double precision) END AS propstrength__tax_percentage__c, "
				
				+ " CASE WHEN CAST(b4.propstrength__new_tax_percentage__c as text) <> CAST('' as text) THEN b4.propstrength__new_tax_percentage__c * cast(2 as double precision)  ELSE cast(0 as double precision) END AS propstrength__new_tax_percentage__c, "
				
				
				+ " b1.propstrength__other_charge_code__c as other_charges_name, "
				+ " b1.propstrength__st_on_completion_certificate__c, "
				+ " b1.propstrength__sta_applicable__c, "
				
				//Add new col for remove other charges
				+ " b1.propstrength__part_of_cop__c, "
				
				
				+ " a.id, " 
				+ " CASE  WHEN a.propstrength__amount_percent__c IS NULL THEN CAST(0 as double precision)  ELSE a.propstrength__amount_percent__c  END AS propstrength__amount_percent__c, "
				+ " a.propstrength__type__c, a.condition_type__c,"
				+ " CASE WHEN a.propstrength__amount__c IS NULL THEN CAST(0 as double precision) ELSE a.propstrength__amount__c END AS propstrength__amount__c, "
				+ " a.propstrength__payment_plan__c, "
				+ " a.sfid, "
				+ " a.propstrength__payment_plan_line_item_name__c, "
				+ " a.name AS paymentplan_name, "
				
				+ " a1.propstrength__amount_percent__c AS other_charge_percent, "
				+ " a1.propstrength__payment_plan_line_item__c, "
				+ " a1.sfid AS sfid_pp_other_charges, "
				+ " a1.propstrength__other_charges__c "
				
				+ " FROM salesforce.propstrength__payment_plan_line_items__c a "
			
				+ " LEFT JOIN salesforce.propstrength__payment_plan_other_charges__c a1 ON  CAST(a1.propstrength__payment_plan_line_item__c as text) = CAST(a.sfid as text) "
				+ " LEFT JOIN salesforce.propstrength__property_charges__c c ON CAST(a1.propstrength__other_charges__c as text) = CAST(c.propstrength__other_charges__c as text) "
				
				
				/* salesforce.vw_prop_other_charges */
				
				+ " LEFT JOIN salesforce.propstrength__other_charges__c b1 ON CAST(c.propstrength__other_charges__c as text) = CAST(b1.sfid as text)  "
				+ " LEFT JOIN salesforce.propstrength__other_charges_tax_mapping__c b2 ON CAST(b1.sfid as text) = CAST(b2.propstrength__other_charges__c as text)  AND CAST(b2.propstrength__tax_name__c as text)  ~~ CAST('CGST%' as text) "
				+ " LEFT JOIN salesforce.propstrength__service_tax_mapping__c b3 ON CAST(b2.propstrength__service_tax_mapping__c as text) =  CAST(b3.sfid as text) "
				+ " LEFT JOIN salesforce.propstrength__tax_record__c b4 ON CAST(b3.propstrength__tax_record__c as text) =  CAST(b4.sfid as text) "
				
				/* END salesforce.vw_prop_other_charges */
				
				
				
				
				//+ " LEFT JOIN salesforce.vw_prop_other_charges b on CAST(a1.propstrength__other_charges__c as text) = CAST(b.propstrength__other_charges__c as text) "
				
				
				
				
				+ " Left Join salesforce.propstrength__property__c d on CAST(c.propstrength__property__c as text)  = CAST(d.sfid as text) "
				+ " Left Join salesforce.propstrength__tower__c e on CAST(d.propstrength__tower__c as text)  = CAST(e.sfid as text) " 
				+ " where   c.propstrength__property__c = '"+unitSfid+"'  and a.propstrength__payment_plan__c = '"+paymentPlanSfid+"' "+conditionString+" order by a.name " , PaymentPlanWithOtherCharge.class);
		authors = (List<PaymentPlanWithOtherCharge>)q.getResultList();
			
			
			System.out.println("Query ZZ 122 ::: " + q);
			
		/* } */
		
		
		
		
		
		
		/*
		 * List<PaymentPlanWithOtherCharge> list =new ArrayList<>(); list=session.
		 * createQuery("  from  PaymentPlanWithOtherCharge where propstrength__payment_plan__c ='"
		 * +paymentPlanSfid+"'  and   propstrength__property__c ='"
		 * +unitSfid+"' order by id ").list(); //where
		 * propstrength__payment_plan__c='"+project_code+"'
		 */
		
		
		
		if(authors.size()>0)
		{
			return authors;
		}
		return null;
	}
	
	
	
	
	

	
	
	
	
	
}