package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AllParkingDataDao;
import com.godrej.properties.model.AllParkingData;

@Repository("allParkingDataDao")
public class AllParkingDataDaoImpl implements AllParkingDataDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<AllParkingData> getParkingReportDtl(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<AllParkingData> authors=null;
		 
		
		/* -------------- Query For Count --------------- */
	  	Query countQuery = session.createNativeQuery(" SELECT  COUNT(id) "
				+ " FROM salesforce.propstrength__car_parking__c a "
				+ " where "+whereCondition+"      ");
	  	
	  		/* -------------- END Query For Count --------------- */
	  	
	  			long count = ((Number) countQuery.getSingleResult()).intValue();
	  	
	  			System.out.println(count);
			   
	  			String strRowCount = Long.toString(count);
	  		  	
	  		  	if (count <= 15000) {
	  		  		
	  		  		/* Final Query */
		  		  	Query q = session.createNativeQuery(" SELECT  row_number() OVER () AS row_no, "
		  					
					+ " c.name as project_name, "
					+ " d.propstrength__tower_name__c as tower_name, "
					+ " a.name, "
					+ " a.propstrength__car_parking_name__c, "
					+ " a.propstrength__double_bay_parking__c, "
					+ " a.propstrength__parking_size__c, "
					+ " a.propstrength__is_released__c, "
					+ " a.propstrength__is_parking_blocked__c, "
					+ " a.propstrength__category_of_parking__c, "
					+ " a.location_of_parking__c, "
					+ " CASE WHEN a.parking_area_sq_ft__c IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.parking_area_sq_ft__c as numeric (20,2))  END AS parking_area_sq_ft__c, "
					+ " CASE WHEN a.parking_area_sq_mt__c IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.parking_area_sq_mt__c as numeric (20,2))  END AS parking_area_sq_mt__c, "
					+ " a.dimensions__c, "
					+ " a.propstrength__approvalstatus__c, " 
					+ " a.allotted_through_offer__c, "
					+ " a.propstrength__allotted__c, "
					+ " b.hold_reason, "
					+ " a.propstrength__active__c, "
					
					+ " CASE WHEN b.hold_status IS NULL THEN false  ELSE (b.hold_status)  END AS hold_status "
					
					+ " FROM salesforce.propstrength__car_parking__c a "
					+ " LEFT JOIN salesforce.gpl_cs_hold_admin_parking b ON a.sfid = b.parkingsfid  and b.hold_status = true "
					+ " LEFT JOIN salesforce.propstrength__projects__c c ON c.sfid = a.propstrength__project__c "
					+ " LEFT JOIN salesforce.propstrength__tower__c d ON d.sfid = a.propstrength__tower_code__c "
					
					+ " where "+whereCondition+" order by c.name, d.propstrength__tower_name__c, a.propstrength__car_parking_name__c ASC  ", AllParkingData.class);
			 
					authors = q.getResultList();
	  		  	} else {
		  		  	List<AllParkingData> lists = new ArrayList<AllParkingData>();
			  		lists = getlist(lists,"MAX_LIMIT",strRowCount);
			  		return lists;
	  		  	}
	  		  
	  		  if (authors.size() > 0) {
	  			  return authors;  
	  		  }
	  		  	
	  		  return null;
	}

	private List<AllParkingData> getlist(List<AllParkingData> list,String msg, String count){
		List<AllParkingData> lists= list;
		if(lists.size()==0) {
			AllParkingData getMisReport=	new AllParkingData();;
			getMisReport.setQry_count(count);
			getMisReport.setQry_msg(msg);
			lists.add(getMisReport);
		}
		return lists;
	}
}	