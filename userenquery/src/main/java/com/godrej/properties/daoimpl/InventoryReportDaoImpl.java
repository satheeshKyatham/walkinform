package com.godrej.properties.daoimpl;

import java.util.List;

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
		
		Query q = session.createNativeQuery(" SELECT  "
				+ " a.gpl_cs_hold_admin_unit_id, "
				+ " a.sfid as unit_sfid, "
				+ " a.customer_id as admin_userid, "
				+ " a.project_id, " 
				+ " a.hold_reason,  "
				+ " a.hold_status,  "
				+ " a.hold_description, " 
				+ " a.hold_behalf_username, "
				+ " b.floor_number__c,  "
				+ " b.propstrength__house_unit_no__c, " 
				+ " b.tower_code__c, "
				+ " b.propstrength__unit_type__c, " 
				+ " c.user_name as admin_name,  "
				+ " c.emailid as admin_emailid, "
				+ " b.propstrength__active__c, "
				+ " b.tower_name__c "
				+ " FROM salesforce.gpl_cs_hold_admin_unit a "
				+ " INNER JOIN salesforce.propstrength__property__c b ON   b.sfid = a.sfid AND b.propstrength__active__c = true "
				+ " LEFT JOIN salesforce.mst_user c ON cast(a.customer_id as integer) = c.user_id "
				+ " where "+whereCondition+"  and a.hold_reason in ('block', 'temp') and  a.hold_status = true  order by b.floor_number__c ", InventoryReport.class);
		
		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}	