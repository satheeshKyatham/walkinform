package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.FloorWiseBookingDao;
import com.godrej.properties.model.FloorWiseBooking;

@SuppressWarnings("unchecked")
@Repository
public class FloorWiseBookingDaoImpl extends AbstractDao<Integer, FloorWiseBooking>implements FloorWiseBookingDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<FloorWiseBooking> getData(String towersfid, String projectsfid) {	 
		String whereCondition = "";
		if (towersfid != null) {
			whereCondition = "a.propstrength__tower__c in ("+towersfid+") AND ";
		} else {
			whereCondition = "";
			return null;
		}

		Session session = this.sessionFactory.getCurrentSession();
		
		List<FloorWiseBooking> authors=null;
		
		Query q = session.createNativeQuery(" SELECT "
				+ " a.id, "
				+ " a.sfid, "
				+ " b.tower_name__c, "
				+ " b.floor_name__c, "
				+ " a.propstrength__primary_applicant_name__c, "
				+ " a.sap_customer_code__c, "
				+ " b.propstrength__floor__c  "
				+ " FROM salesforce.propstrength__application_booking__c a "
				+ " INNER JOIN salesforce.propstrength__property__c b ON a.propstrength__property__c = b.sfid  "
					+ " AND a.propstrength__project__c = '"+projectsfid+"' "
					+ " AND a.propStrength__status__c = 'Deal Approved' "
					+ " AND a.deal_status__c = 'Approved' "
					+ " AND b.propstrength__floor__c in ("+towersfid+") "
				+ " order by   b.tower_name__c, b.propstrength__floor_number__c ASC  ", FloorWiseBooking.class);
		
		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}

}