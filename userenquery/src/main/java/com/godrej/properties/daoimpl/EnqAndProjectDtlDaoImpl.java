package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.EnqAndProjectDtlDao;
import com.godrej.properties.model.EnqAndOfferDtl;
import com.godrej.properties.model.EnqAndProjectDtl;

@Repository("enqAndProjectDtlDao")
public class EnqAndProjectDtlDaoImpl extends AbstractDao<Integer, EnqAndProjectDtl> implements EnqAndProjectDtlDao{
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public List<EnqAndProjectDtl> getEnqAndProjectData(String enqId) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<EnqAndProjectDtl> authors=null;
		
		Query q = session.createNativeQuery("SELECT "
				
				+ " a.id, "
				+ " a.closing_manager_name__c, "
				+ " a.verticle__c, "
				+ " a.walk_in_source__c, "
				+ " a.propstrength__enquiry_type__c, "
				+ " a.propstrength__primary_contact__c, "
				+ " a.date_of_eoi__c, "
				+ " a.sourcing_manager_name__c, "
				+ " a.name as enq_name, "
				+ " b.name as broker_name, "
				+ " b.mobile_no__c as broker_mobile, "
				+ " c.marketing_project_name__c, "
				+ " c.region__c, "
				+ " c.name as projectNameWithoutCity "

				+ " FROM salesforce.propstrength__request__c a "
				+ " LEFT JOIN salesforce.account b ON a.propStrength__broker_account__c = b.sfid  and b.recordtypeid = '0126F000000uazCQAQ' "
				+ " INNER JOIN salesforce.propstrength__projects__c c ON a.propstrength__project__c = c.sfid "
				
				+ " where a.sfid = '"+enqId+"'   ", EnqAndProjectDtl.class);

		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}