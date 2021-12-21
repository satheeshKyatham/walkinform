package com.godrej.properties.daoimpl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.InventoryReportDao;
import com.godrej.properties.model.InventoryReport;
import com.godrej.properties.model.PaymentEOIReport;

@Repository("inventoryReportDao")
public class InventoryReportDaoImpl implements InventoryReportDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<InventoryReport> getInventoryReportDtl(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<InventoryReport> authors=null;
		
		Query q = session.createNativeQuery(" SELECT  row_number() OVER () AS row_no, "
				
				+ " e.walk_in_source__c, "
				+ " e.sourcing_manager_name__c, "
				+ " i.name as broker_name, "
				
				
				+ " e.name as enq_name, "
				+ " f.name as customer_name, "
				+ " f.mobile__c as customer_mobile, "
				+ " a.enq_sfid, "
				+ " a.eoi_unit_locked, "
				+ " a.gpl_cs_hold_admin_unit_id, "
				+ " a.created_at, "
				+ " a.sfid as unit_sfid, "
				+ " a.customer_id as admin_userid, "
				+ " a.project_id, " 
				+ " a.hold_reason,  "
				+ " a.hold_status,  "
				+ " a.hold_description, " 
				
				//+ " b.floor_number__c,  "
				+ " CASE WHEN b.floor_number__c IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(b.floor_number__c as numeric (20,2))  END AS floor_number__c, "
				
				+ " b.propstrength__house_unit_no__c, " 
				+ " b.tower_code__c, "
				+ " b.propstrength__unit_type__c, " 
				+ " c.user_name as admin_name,  "
				+ " c.emailid as admin_emailid, "
				
				//+ " b.propstrength__active__c, "
				+ " CASE WHEN b.propstrength__active__c IS NULL THEN cast(false as boolean) ELSE b.propstrength__active__c END AS propstrength__active__c, "
				
				+ " b.tower_name__c, "
				+ " b.wing_block__c, "
				+ " b.saleable_area__c, "
				+ " b.propstrength__rate_per_unit_area__c, "
				+ " d.user_name as hold_behalf_username, "
				+ " d.emailid as hold_behalf_email, "
				
				
				+ " CASE WHEN h.propstrength__part_of_cop__c IS NULL THEN cast(false as boolean) ELSE h.propstrength__part_of_cop__c END AS propstrength__part_of_cop__c, "
				+ " g.propstrength__type__c, "
				+ " CASE WHEN g.propstrength__rate_per_unit_area__c IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(g.propstrength__rate_per_unit_area__c as numeric (20,2))  END AS othercharge__rate_per_unit_area__c, "
				+ " CASE WHEN g.propstrength__fixed_charge__c IS NULL THEN cast(0 as numeric (20,2)) ELSE cast(g.propstrength__fixed_charge__c as numeric (20,2)) END AS propstrength__fixed_charge__c, "
				
				+ " e.verticle__c, "
				+ " e.closing_manager_name__c, "
				
				//Parking
				+ " p.parkingname"
				//END Parking
				
				+ " FROM salesforce.gpl_cs_hold_admin_unit a "
				+ " LEFT JOIN salesforce.propstrength__property__c b ON   b.sfid = a.sfid AND b.propstrength__active__c = true "
				
				//Parking
				+ " LEFT JOIN (SELECT x.flatsfid, string_agg(y.propstrength__car_parking_name__c, ', ') AS parkingname  FROM salesforce.gpl_cs_hold_admin_parking x  "
					+ " LEFT JOIN salesforce.propstrength__car_parking__c y ON x.parkingsfid = y.sfid "
					+ " where  x.hold_reason = 'temp' AND x.hold_status = true "
					+ " GROUP  BY 1 ) p ON p.flatsfid = a.sfid "
				//END Parking
				
				+ " LEFT JOIN salesforce.mst_user c ON cast(a.customer_id as integer) = c.user_id "
				+ " LEFT JOIN salesforce.mst_user d ON a.hold_behalf_userid = d.user_id  "
				+ " LEFT  JOIN salesforce.propstrength__request__c e ON e.sfid = a.enq_sfid " 
				+ " LEFT JOIN salesforce.contact f ON f.sfid = e.propstrength__primary_contact__c "
				
+ " LEFT JOIN salesforce.account i ON i.sfid = e.propstrength__broker_account__c AND e.isdeleted = false "
				
				+ " LEFT JOIN salesforce.propstrength__property_charges__c g ON g.propstrength__property__c = a.sfid "
				+ " LEFT JOIN salesforce.propstrength__other_charges__c h ON CAST(g.propstrength__other_charges__c as text)  = CAST(h.sfid as text) "
				
				+ " where "+whereCondition+"  and a.hold_reason in ('block', 'temp') and  a.hold_status = true  order by a.created_at desc  ", InventoryReport.class);
				
				//+ " where "+whereCondition+"     order by a.created_at desc  ", InventoryReport.class);
		
				//and a.hold_reason in ('block', 'temp') and  a.hold_status = true 
		
		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}	