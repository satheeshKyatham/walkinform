package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.FlatDtlProjectWiseDao;
import com.godrej.properties.model.FlatDtlProjectWise;

@Repository("flatDtlProjectWiseDao")
public class FlatDtlProjectWiseDaoImpl extends AbstractDao<Integer, FlatDtlProjectWise> implements FlatDtlProjectWiseDao{
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public List<FlatDtlProjectWise> getFlatForAdminUnitHold(String flatName, String projectSFID) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<FlatDtlProjectWise> enqDtl=null;
		
		Query qry = session.createNativeQuery("SELECT "
				+ " id, "
				+ " tower_name__c, "
				+ " floor_name__c, "
				+ " sfid, "
				+ " propstrength__property_name__c, "
				+ " propstrength__house_unit_no__c, "
				+ " propstrength__unit_type__c "
				
				+ " FROM salesforce.propstrength__property__c "
				+ " where propstrength__property_name__c = '"+flatName+"' "
				+ " AND project18digit__c = '"+projectSFID+"' "
				+ " AND propstrength__active__c = true "
				+ " AND propstrength__allotted__c = false "
				+ " AND propstrength__property_alloted_through_offer__c = false " , FlatDtlProjectWise.class);

		enqDtl = qry.getResultList();
		
		if (enqDtl.size() > 0)
			return enqDtl;

		return null;
	}
}