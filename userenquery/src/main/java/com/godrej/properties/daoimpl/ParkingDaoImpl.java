package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.ParkingDao;
import com.godrej.properties.model.Parking;
import com.godrej.properties.model.ParkingAdmin;

@Repository("parkingDao")
public class ParkingDaoImpl extends AbstractDao<Integer, Parking> implements ParkingDao{

	@Autowired
	private SessionFactory sessionFactory;	
	
	@SuppressWarnings("unchecked")
	
	//public List<Parking> getParkingDtl(String propertyTypeSfid, String project_code, String towerMst, String typoMst, String holdMst, String soldMst,String facing, String unitAvailable, String unitCategory) {	 
	public List<Parking> getParkingDtl(String propertyTypeSfid, String projectId, String towerMst, String unitCategory, String parkingLocation) {	
		 
		String category = "";
		String location = "";
		
		if (unitCategory.equals("All")) {
			category = "";
		} else {
			category = "and a.propstrength__parking_size__c = '"+unitCategory+"'";
		}
		
		if (parkingLocation.equals("All")) {
			location = "";
		} else if (parkingLocation.equals("-")) {
			location = "and a.location_of_parking__c IS NULL";
		} else {
			location = "and a.location_of_parking__c = '"+parkingLocation+"'";
		}
		
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<Parking> authors =null;
		
		Query q = session.createNativeQuery(" SELECT "
				+ " a.id, "
				+ " a.sfid, "
				+ " a.propstrength__car_parking_name__c, "
				+ " a.propstrength__parking_type__c, "
				+ " a.propstrength__category_of_parking__c, "
				+ " a.propstrength__parking_size__c, "
				+ " a.property__c, "
				+ " a.propstrength__project__c, "
				+ " a.propstrength__tower_name__c, "
				+ " a.propstrength__is_released__c, "
				+ " a.propstrength__is_parking_blocked__c, "
				//+ " a.propstrength__allotted__c, "
				+ " a.propstrength__active__c,"
				+" CASE  WHEN b.absolute_amount  IS NULL  THEN -1  ELSE absolute_amount  END AS absolute_amount, "
				
				//new
				+ " c.holdstatusyn, "
				+ " c.statusai, "
				+ " c.created_at, "
				+ " CASE WHEN c.hold_for_time IS NULL THEN 0 ELSE c.hold_for_time END AS hold_for_time, "
				+ " CASE "
		            + " WHEN c.user_id IS NULL THEN '-1'\\:\\:integer "
		            + " ELSE c.user_id "
		        + " END AS hold_user_id, "

				+ " CASE WHEN d.hold_status = true THEN true ELSE a.propstrength__allotted__c END AS propstrength__allotted__c, "
				+ " d.hold_status, "
				+ " d.hold_reason, "
				+ " d.flatsfid, "
				+ " CASE "
					+ " WHEN a.propstrength__allotted__c IS NULL THEN false "
					+ " ELSE a.propstrength__allotted__c "
				+ " END AS sfdc_propstrength__allotted__c, "
				//END New
				+ " a.location_of_parking__c,"
				+ " a.propstrength__tower_code__c, "
				
				+ " CASE "
					+ " WHEN a.allotted_through_offer__c IS NULL THEN false "
					+ " ELSE a.allotted_through_offer__c "
				+ " END AS allotted_through_offer__c "
				
				
				+ " FROM salesforce.propstrength__car_parking__c a"
				+ " LEFT JOIN salesforce.nv_parking_mapping b ON a.propstrength__category_of_parking__c = b.parking_category AND b.property_type_sfid = '"+propertyTypeSfid+"' AND b.tower_sfid = '"+towerMst+"' "
				
				//New
				+ " LEFT JOIN salesforce.gpl_cs_hold_parking c ON c.parkingsfid = a.sfid AND c.holdstatusyn <> 'N' AND c.statusai <> 'I' "
				+ " LEFT JOIN salesforce.gpl_cs_hold_admin_parking d ON a.sfid = d.parkingsfid AND d.projectsfid = a.propstrength__project__c AND d.version = 0 "
				// END New
				
				+ " where a.propstrength__project__c = '"+projectId+"' "
				+ " AND a.propstrength__active__c='t' "
				+ " AND  a.propstrength__tower_code__c = '"+towerMst+"'   "
				+ category
				+ location
				+ " ORDER BY a.propstrength__category_of_parking__c,  "
				//+ " location_of_parking__c, "
				+ " a.propstrength__car_parking_name__c  ", Parking.class);
				
				//+ " where  "+whereCondition+" order by name ", PaymentPlanList.class);
			
			authors = (List<Parking>)q.getResultList();
			
		if(authors.size()>0)
		{
			return authors;
		}
		return null;
		
	}
	
	
	
	//public List<ParkingAdmin> getAdminParkingDtl(String propertyTypeSfid, String project_code, String towerMst, String typoMst, String holdMst, String soldMst,String facing, String unitAvailable, String unitCategory) {	 
	public List<ParkingAdmin> getAdminParkingDtl(String propertyTypeSfid, String projectId, String towerMst, String unitCategory, String parkingLocation, String searchadminparking) {	
		 
		String category = "";
		String location = "";
		String adminParkingFilter ="";
		
		
		if (unitCategory.equals("All")) {
			category = "";
		} else {
			category = "and a.propstrength__parking_size__c = '"+unitCategory+"'";
		}
		
		if (parkingLocation.equals("All")) {
			location = "";
		} else if (parkingLocation.equals("-")) {
			location = "and a.location_of_parking__c IS NULL";
		} else {
			location = "and a.location_of_parking__c = '"+parkingLocation+"'";
		}
		
		if (searchadminparking.equals("All")) {
			adminParkingFilter = "";
		} else if(searchadminparking.equals("f")) {
			adminParkingFilter = " and a.propstrength__allotted__c = false AND c.hold_status IS NULL ";
		}else if (searchadminparking.equals("t")) {
			adminParkingFilter = " and c.hold_status = true ";
		}
		
		
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<ParkingAdmin> authors =null;
		
		Query q = session.createNativeQuery(" SELECT "
				+ " a.id,"
				+ " a.sfid," 
				+ " a.propstrength__car_parking_name__c," 
				+ " a.propstrength__parking_type__c, "
				+ " a.propstrength__category_of_parking__c," 
				+ " a.propstrength__parking_size__c, "
				+ " a.property__c, "
				+ " a.propstrength__project__c," 
				+ " a.propstrength__tower_name__c, " 
				+ " a.propstrength__is_released__c, "
				+ " a.propstrength__is_parking_blocked__c, " 
				//+ " a.propstrength__allotted__c, "
				+ " a.propstrength__active__c, "
				
				+ " b.holdstatusyn, "
				+ " b.statusai as holdIntervalstatusAI, "
				+ " b.created_at, "
				+ " CASE  WHEN b.user_id IS NULL THEN '-1' ELSE b.user_id  END AS hold_user_id, "
				+ " CASE WHEN b.hold_for_time IS NULL THEN 0 ELSE b.hold_for_time END AS holdForTime, "
				+ " CASE WHEN c.hold_status = true THEN true ELSE a.propstrength__allotted__c END AS propstrength__allotted__c, "
				+ " c.hold_status, "
				+ " c.hold_reason, "
				
				+" CASE  WHEN d.absolute_amount  IS NULL  THEN -1  ELSE absolute_amount  END AS absolute_amount,  "
				+ " a.location_of_parking__c,"
				+ " a.propstrength__tower_code__c, "
				
				+ " CASE "
					+ " WHEN a.allotted_through_offer__c IS NULL THEN false "
					+ " ELSE a.allotted_through_offer__c "
				+ " END AS allotted_through_offer__c "
				
				+ " FROM salesforce.propstrength__car_parking__c a "
				
				+ " LEFT JOIN salesforce.nv_parking_mapping d ON a.propstrength__category_of_parking__c = d.parking_category AND d.property_type_sfid = '"+propertyTypeSfid+"' AND d.tower_sfid = '"+towerMst+"' "
				
				+ " LEFT JOIN salesforce.gpl_cs_hold_parking b ON a.sfid = b.parkingsfid AND b.holdstatusyn <> 'N' AND b.statusai <> 'I' "
				+ " LEFT JOIN salesforce.gpl_cs_hold_admin_parking c ON a.sfid = c.parkingsfid AND c.projectsfid = a.propstrength__project__c AND c.version = 0 "
				  
				+ " where a.propstrength__project__c = '"+projectId+"' "
				+ " AND a.propstrength__active__c='t' "
				+ " AND  a.propstrength__tower_code__c = '"+towerMst+"'   "
				+ category
				+ location
				+ adminParkingFilter
				+ " ORDER BY a.propstrength__category_of_parking__c,  "
				+ " a.propstrength__car_parking_name__c  ", ParkingAdmin.class);
			
			authors = (List<ParkingAdmin>)q.getResultList();
			
		if(authors.size()>0)
		{
			return authors;
		}
		return null;
		
	}
	
	@Override
	public List<Parking> getLocation(String towersfid) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Object>  list  =(List<Object>)session.createQuery( " select Distinct(location_of_parking__c),propstrength__tower_code__c FROM  Parking  where propstrength__tower_code__c='"+towersfid+"'" + " order by location_of_parking__c" ).list();
		if(list.size()>0)
		{
			List<Parking> parkingDtl = new ArrayList<Parking>();
	
			 Iterator itr = list.iterator();
			  while(itr.hasNext()){
				  Parking paymentPlan= new Parking();
			     Object[] obj = (Object[]) itr.next();
			      
			     String location = String.valueOf(obj[0]); 
			     String tower = String.valueOf(obj[1]); 
			     
			     if (!location.equals("null")) {
			    	 paymentPlan.setLocation_of_parking__c(location);
			     } else {
			    	 paymentPlan.setLocation_of_parking__c("-");
			     }
			     paymentPlan.setPropstrength__tower_code__c(tower); 
			     
			     parkingDtl.add(paymentPlan);
			  }
			 
			return parkingDtl;
		}
		return new ArrayList<>();
	}
	
	@Override
	public String updateParkingStatus(String parkingsfid) {
		Session session = this.sessionFactory.getCurrentSession();	
		Query q = session.createNativeQuery(" update salesforce.propstrength__car_parking__c set allotted_through_offer__c='t' where sfid='"+parkingsfid+"'");
		q.executeUpdate();
		return "updated";
		
	}
	
	@Override
	public Parking getHeldUnit(String parkingsfid) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		try {
			if (session == null) {
				return null;
			}
			
			List<Parking> authors =null;
			
			Query q = session.createNativeQuery(" SELECT "
					+ " a.id, "
					+ " a.sfid, "
					+ " a.propstrength__active__c,"
					+ " c.holdstatusyn "
					      
					+ " FROM salesforce.propstrength__car_parking__c a"
					 
					+ " LEFT JOIN salesforce.gpl_cs_hold_parking c ON c.parkingsfid = a.sfid "
					
					+ " where a.propstrength__active__c= true "
					+ " AND a.sfid = '"+parkingsfid+"' "
					+ " AND c.holdstatusyn='Y' ", Parking.class);
				
				authors = (List<Parking>)q.getResultList();
				
			if(authors.size()>0) {
				return authors.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}	