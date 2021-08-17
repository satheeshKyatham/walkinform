package com.godrej.properties.daoimpl;

import java.sql.Timestamp;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.EdpedrDao;
import com.godrej.properties.model.Edpedr;

@SuppressWarnings("unchecked")
@Repository
public class EdpedrDaoImpl extends AbstractDao<Integer, Edpedr>implements EdpedrDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer updateData(String multiFloor, String projectsfid, String edpDate) {
		
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		 
		Query query = session.createNativeQuery(" UPDATE   salesforce.propstrength__application_booking__c SET EDP__c = '"+edpDate+"' "
				+ " where propstrength__status__c = 'Deal Approved'  "
				+ " AND deal_status__c = 'Approved'  "
				+ " AND propstrength__project__c = '"+projectsfid+"' AND propstrength__property__c IN ( "
					+ "	SELECT sfid FROM salesforce.propstrength__property__c "
					+ " where project18digit__c = '"+projectsfid+"' AND propstrength__floor__c in ("+multiFloor+") "
				+ " ) " );
		 
		Integer edpUpdate = query.executeUpdate();
		return edpUpdate;
	}
	
	@Override
	public Integer updateEDRData(String bookingsfid, String projectsfid, String edrDate) {
		
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		 
		Query query = session.createNativeQuery(" UPDATE   salesforce.propstrength__application_booking__c SET EDR__c = '"+edrDate+"' "
				+ " where propstrength__status__c = 'Deal Approved'  "
				+ " AND deal_status__c = 'Approved'  "
				+ " AND propstrength__project__c = '"+projectsfid+"' AND sfid in ("+bookingsfid+")  " );
		 
		Integer edpUpdate = query.executeUpdate();
		return edpUpdate;
	}
	
	
	@Override
	public Boolean insertData(String multiFloor, String projectsfid, String edpDate, String userid, String multiTower) {
		
		Session session = this.sessionFactory.getCurrentSession();	
		
		Timestamp currentTpm = new Timestamp(System.currentTimeMillis());
		
		Query query = session.createNativeQuery("INSERT INTO salesforce.nv_edp_log "
				+ " (projectsfid, tower_multiple_sfid, floor_multiple_sfid, newdate, created, createdby, isactive) "
				+ " VALUES ('"+projectsfid+"', '"+multiTower+"', '"+multiFloor+"', '"+edpDate+"', '"+currentTpm+"', "+userid+", 'Y')  ");
		
		query.executeUpdate();
		
		return true;
	}
	
	@Override
	public Boolean insertDataEDR(String selectedFloor, String projectsfid, String edrDate, String userid, String selectedTower, String bookingsfid) {
		
		Session session = this.sessionFactory.getCurrentSession();	
		  
		Timestamp currentTpm = new Timestamp(System.currentTimeMillis());
		
		Query query = session.createNativeQuery("INSERT INTO salesforce.nv_edr_log "
				+ " (projectsfid, tower_multiple_sfid, floor_multiple_sfid, booking_multiple_sfid, newdate, created, createdby, isactive) "
				+ " VALUES ('"+projectsfid+"', '"+selectedTower+"', '"+selectedFloor+"', '"+bookingsfid+"', '"+edrDate+"', '"+currentTpm+"', "+userid+", 'Y')  ");
		
		query.executeUpdate();
		
		return true;
	}
}