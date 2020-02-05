package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.EnqDtlProjectWiseDao;
import com.godrej.properties.model.EnqDtlProjectWise;

@Repository("enqDtlProjectWiseDao")
public class EnqDtlProjectWiseDaoImpl extends AbstractDao<Integer, EnqDtlProjectWise> implements EnqDtlProjectWiseDao{
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public List<EnqDtlProjectWise> getEnqForAdminUnitHold(String enqName, String projectSFID) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<EnqDtlProjectWise> enqDtl=null;
		
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
				
				//+ " b.name as broker_name, "
				//+ " b.mobile_no__c as broker_mobile, "
				
				//+ " c.marketing_project_name__c, "
				//+ " c.region__c, "
				//+ " c.name as projectNameWithoutCity "

				+ " a.sfid, "
				+ " b.mobile__c, "
				+ " b.name   "
				
				
				+ " FROM salesforce.propstrength__request__c a "
				+ " INNER JOIN salesforce.contact b ON a.propstrength__primary_contact__c = b.sfid  "
				//+ " INNER JOIN salesforce.propstrength__projects__c c ON a.propstrength__project__c = c.sfid "
				
				+ " where a.name = '"+enqName+"'  AND a.propstrength__project__c = '"+projectSFID+"' ", EnqDtlProjectWise.class);

		enqDtl = qry.getResultList();
		
		if (enqDtl.size() > 0)
			return enqDtl;

		return null;
	}
}