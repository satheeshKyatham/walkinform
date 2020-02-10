package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.AdminUnitHoldStatusDao;
import com.godrej.properties.model.ApplicationDtl;
import com.godrej.properties.model.HoldInventoryAdmin;
import com.godrej.properties.model.UnitDtl;

@Repository("adminUnitHoldStatusDao")
public class AdminUnitHoldStatusDaoImpl extends AbstractDao<Integer, HoldInventoryAdmin> implements AdminUnitHoldStatusDao{
	@Autowired
	private SessionFactory sessionFactory;	
	
	/*@Override
	public List<HoldInventoryAdmin> getAdminUnitHold(String unitSFID) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<HoldInventoryAdmin> enqDtl=null;
		
		Query qry = session.createNativeQuery("SELECT "
				
				+ " a.id, "
				+ " a.closing_manager_name__c, "
				+ " a.verticle__c, "
				+ " a.walk_in_source__c, "
				+ " a.propstrength__enquiry_type__c, "
				+ " a.propstrength__primary_contact__c, "
				+ " a.date_of_eoi__c, "
				+ " a.sourcing_manager_name__c, "
				+ " a.name as enq_name, "
			 
				+ " a.sfid, "
				+ " b.mobile__c, "
				+ " b.name   "
				
				
				+ " FROM salesforce.propstrength__request__c a "
				+ " INNER JOIN salesforce.contact b ON a.propstrength__primary_contact__c = b.sfid  "
				
				+ " where a.name = '"+unitSFID+"'   ", HoldInventoryAdmin.class);

		enqDtl = qry.getResultList();
		
		if (enqDtl.size() > 0)
			return enqDtl;

		return null;
	}*/
	
	
	
	
	public Boolean getAdminUnitHold(String unitSFID) {	 
		
		/*Criteria criteria = createEntityCriteria();		
		criteria.add(Restrictions.eq("sfid", unitSFID));
		
		
		List<HoldInventoryAdmin> req= (List<HoldInventoryAdmin>) criteria.list();
		if(req.size()>0) {
			return true;
		}
		return null;	*/
		
		
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<HoldInventoryAdmin> authors=null;
		
		Query q = session.createNativeQuery(" select * from salesforce.gpl_cs_hold_admin_unit "
				+ " where sfid = '"+unitSFID+"' and  hold_status = true ", HoldInventoryAdmin.class);

		authors = q.getResultList();
		
		if (authors.size() > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
}