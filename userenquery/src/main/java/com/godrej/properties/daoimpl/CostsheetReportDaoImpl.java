package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.CostsheetReportDao;
import com.godrej.properties.model.CostsheetReport;

@Repository("costsheetReportDao")
public class CostsheetReportDaoImpl implements CostsheetReportDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<CostsheetReport> getReportDtl(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<CostsheetReport> authors=null;
		 
		
		/* -------------- Query For Count --------------- */
	  	Query countQuery = session.createNativeQuery(" SELECT  COUNT(*) "
				+ " FROM salesforce.nv_d4u_costsheet_log a "
				/*+ " LEFT JOIN salesforce.nv_token b ON b.nv_token_id = a.token_id "
				+ " LEFT JOIN salesforce.contact c ON c.sfid = a.contact_sfid "
				+ " LEFT JOIN salesforce.propstrength__request__c d ON d.sfid = a.enquiry_sfid"
				+ " LEFT JOIN salesforce.propstrength__projects__c e ON e.sfid = a.project_sfid"
				+ " LEFT JOIN salesforce.propstrength__tower__c f ON f.sfid = a.tower_sfid"
				+ " LEFT JOIN salesforce.propstrength__property__c g ON g.sfid = a.inventory_sfid"
				+ " LEFT JOIN salesforce.propstrength__payment_plan__c h ON h.sfid = a.paymentplan_sfid"
				+ " LEFT JOIN salesforce.mst_user i ON i.user_id = a.createdby "*/
				+ " where "+whereCondition+"      ");
	  	
	  		/* -------------- END Query For Count --------------- */
	  	
	  			long count = ((Number) countQuery.getSingleResult()).intValue();
	  	
	  			System.out.println(count);
			   
	  			String strRowCount = Long.toString(count);
	  		  	
	  		  	if (count <= 5000) {
	  		  		
	  		  		/* Final Query */
		  		  	Query q = session.createNativeQuery(" SELECT  row_number() OVER () AS row_no, "
					+ " a.createddate, a.source, a.costsheet_type, a.scheme_type, a.scheme_name, "
					
					+ " CASE WHEN a.scheme_rate IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.scheme_rate as numeric (20,2))  END AS scheme_rate, "
					+ " CASE WHEN a.scheme_absolute IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.scheme_absolute as numeric (20,2))  END AS scheme_absolute, "
					+ " CASE WHEN a.scheme_percentage IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.scheme_percentage as numeric (20,2))  END AS scheme_percentage, "
					+ " a.scheme_zero_govt_charges, "
					+ " a.carpark_type, "
					+ " CASE WHEN a.carpark_count IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.carpark_count as numeric (20,2))  END AS carpark_count, "
					//---------------------------------------------------------------------------------------------
					+ " CASE WHEN a.discounted_bsp IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.discounted_bsp as numeric (20,2))  END AS discounted_bsp, "
					+ " CASE WHEN a.og_bsp IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.og_bsp as numeric (20,2))  END AS og_bsp, "
					+ " CASE WHEN a.carpet_area_sqft IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.carpet_area_sqft as numeric (20,2))  END AS carpet_area_sqft, "
					+ " CASE WHEN a.saleable_area_sqft IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.saleable_area_sqft as numeric (20,2))  END AS saleable_area_sqft, "
					+ " CASE WHEN a.carpet_area_rera_sqmt IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.carpet_area_rera_sqmt as numeric (20,2))  END AS carpet_area_rera_sqmt, "
					+ " CASE WHEN a.exclusive_area_sqmt IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.exclusive_area_sqmt as numeric (20,2))  END AS exclusive_area_sqmt, "
					+ " CASE WHEN a.total_area_sqmt IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.total_area_sqmt as numeric (20,2))  END AS total_area_sqmt, "
					+ " CASE WHEN a.carpet_area_amount IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.carpet_area_amount as numeric (20,2))  END AS carpet_area_amount, "
					+ " CASE WHEN a.exclusive_area_amount IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.exclusive_area_amount as numeric (20,2))  END AS exclusive_area_amount, "
					+ " CASE WHEN a.flat_unit_cost IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.flat_unit_cost as numeric (20,2))  END AS flat_unit_cost, "
					+ " CASE WHEN a.total_a IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.total_a as numeric (20,2))  END AS total_a, "
					+ " CASE WHEN a.total_b IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.total_b as numeric (20,2))  END AS total_b, "
					+ " CASE WHEN a.stemp_duty_amount IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.stemp_duty_amount as numeric (20,2))  END AS stemp_duty_amount, "
					+ " CASE WHEN a.registration_amount IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.registration_amount as numeric (20,2))  END AS registration_amount, "
					
					
+ " CASE WHEN a.gst_amount IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.gst_amount as numeric (20,2))  END AS gst_amount, "
+ " CASE WHEN a.total_c IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.total_c as numeric (20,2))  END AS total_c, "
+ " CASE WHEN a.total_abc IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.total_abc as numeric (20,2))  END AS total_abc, "
+ " CASE WHEN a.total_discount IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.total_discount as numeric (20,2))  END AS total_discount, "
+ " CASE WHEN a.paymentplan_total IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.paymentplan_total as numeric (20,2))  END AS paymentplan_total, "

					//---------------------------------------------------------------------------------------------
					 
					+ " a.cs_sales_comments, "
					+ " a.property_name, "
					+ " b.type, "
					+ " b.queue , "
					+ " c.mobile__c, c.name as customer_name, "
					+ " d.name as enq_name, "
					+ " e.name as project_name, "
					+ " f.propstrength__tower_name__c, "
					+ " g.propstrength__property_name__c, "
					+ " h.name as paymentplan_name, "
					+ " i.user_name,  "
					+ " i.emailid, "
					
					//Parking
					+ " a.parking_selection, "
					+ " a.parking_name, "
					+ " a.parking_sfid, "
					+ " CASE WHEN a.parking_amount IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.parking_amount as numeric (20,2))  END AS parking_amount "
					
					//END Parking
					
					+ " FROM salesforce.nv_d4u_costsheet_log a "
					+ " LEFT JOIN salesforce.nv_token b ON b.nv_token_id = a.token_id "
					+ " LEFT JOIN salesforce.contact c ON c.sfid = a.contact_sfid "
					+ " LEFT JOIN salesforce.propstrength__request__c d ON d.sfid = a.enquiry_sfid "
					+ " LEFT JOIN salesforce.propstrength__projects__c e ON e.sfid = a.project_sfid "
					+ " LEFT JOIN salesforce.propstrength__tower__c f ON f.sfid = a.tower_sfid "
					+ " LEFT JOIN salesforce.propstrength__property__c g ON g.sfid = a.inventory_sfid "
					+ " LEFT JOIN salesforce.propstrength__payment_plan__c h ON h.sfid = a.paymentplan_sfid "
					+ " LEFT JOIN salesforce.mst_user i ON i.user_id = a.createdby "
					
				  
					+ " where "+whereCondition+"  order by e.name, f.propstrength__tower_name__c, a.property_name  desc  ", CostsheetReport.class);
			 
					authors = q.getResultList();
	  		  	} else {
		  		  	List<CostsheetReport> lists = new ArrayList<CostsheetReport>();
			  		lists = getlist(lists,"MAX_LIMIT",strRowCount);
			  		return lists;
	  		  	}
	  		  
	  		  if (authors.size() > 0) {
	  			  return authors;  
	  		  }
	  		  	
	  		  return null;
	}

	private List<CostsheetReport> getlist(List<CostsheetReport> list,String msg, String count){
		List<CostsheetReport> lists= list;
		if(lists.size()==0) {
			CostsheetReport getMisReport=	new CostsheetReport();;
			getMisReport.setQry_count(count);
			getMisReport.setQry_msg(msg);
			lists.add(getMisReport);
		}
		return lists;
	}
}	