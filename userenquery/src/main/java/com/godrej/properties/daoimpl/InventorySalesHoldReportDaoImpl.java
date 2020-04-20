package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.InventorySalesHoldReportDao;
import com.godrej.properties.model.InventorySalesHoldReport;

@SuppressWarnings("unchecked")
@Repository("inventorySalesHoldReportDao")
public class InventorySalesHoldReportDaoImpl implements InventorySalesHoldReportDao {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<InventorySalesHoldReport> holdDataExist(String whereCondition) {
		try {

			Session session = this.sessionFactory.getCurrentSession();	
			
			List<InventorySalesHoldReport> authors=null;
			
			Query q = session.createNativeQuery(" SELECT  "
					+ " a.gpl_cs_hold_unit_id, "
					+ " a.project_id, "
					+ " a.holdstatusyn, "
					+ " a.statusai, "
					+ " b.tower_code__c, " 
					+ " b.tower_name__c,  "
					+ " b.floor_number__c,  "
					+ " b.propstrength__unit_type__c, " 
					+ " b.propstrength__house_unit_no__c,  "
					
					+ " b.wing_block__c,  "
					+ " b.saleable_area__c,  "
					+ " b.propstrength__rate_per_unit_area__c,  "
					
					+ " c.user_name as held_by_name, " 
					+ " c.emailid as held_by_email, "
					+ " a.created_at " 
					+", coalesce(a.hold_for_time,0) as hold_for_time" 
					+ " FROM salesforce.gpl_cs_hold_unit_uat a "
					+ " INNER JOIN salesforce.propstrength__property__c b ON   b.sfid = a.sfid AND b.propstrength__active__c = true   "
					+ " LEFT JOIN salesforce.mst_user c ON  a.user_id = c.user_id   "
					+ " where "+whereCondition+"    ", InventorySalesHoldReport.class);
			
			authors = q.getResultList();
			
			if (authors.size() > 0)
				return authors;

			return null;
			
			
		} catch (Exception e) {
			logger.info("Exception ",e);
		}

		return null;
	}
}