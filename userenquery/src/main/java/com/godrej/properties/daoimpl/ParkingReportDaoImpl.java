package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.ParkingReportDao;
import com.godrej.properties.model.ParkingReport;

@Repository("parkingReportDao")
public class ParkingReportDaoImpl implements ParkingReportDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ParkingReport> getParkingReportDtl(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<ParkingReport> authors=null;
		
		Query q = session.createNativeQuery(" SELECT  row_number() OVER () AS row_no, "
				+ " a.id, "
				+ " a.created_at, "
				+ " a.hold_reason,  "
				+ " f.emailid as admin_emailid, "
				
				+ " CASE WHEN d.propstrength__tower_name__c IS NULL THEN '-'  ELSE d.propstrength__tower_name__c END AS propstrength__tower_name__c,  "
				+ " CASE WHEN c.propstrength__house_unit_no__c IS NULL THEN '-'  ELSE c.propstrength__house_unit_no__c END AS propstrength__house_unit_no__c,  "				
				+ " CASE WHEN c.propstrength__property_name__c IS NULL THEN '-'  ELSE c.propstrength__property_name__c END AS propstrength__property_name__c,  "
				+ " CASE WHEN e.propstrength__tower_name__c IS NULL THEN '-'  ELSE e.propstrength__tower_name__c END AS parking_tower,  "
				+ " CASE WHEN b.propstrength__car_parking_name__c IS NULL THEN '-'  ELSE b.propstrength__car_parking_name__c END AS propstrength__car_parking_name__c,  "
				+ " CASE WHEN b.propstrength__parking_type__c IS NULL THEN '-'  ELSE b.propstrength__parking_type__c END AS propstrength__parking_type__c,  "
				+ " CASE WHEN b.propstrength__category_of_parking__c IS NULL THEN '-'  ELSE b.propstrength__category_of_parking__c END AS propstrength__category_of_parking__c,  "
				+ " CASE WHEN b.propstrength__parking_size__c IS NULL THEN '-'  ELSE b.propstrength__parking_size__c END AS propstrength__parking_size__c,  "
				+ " CASE WHEN b.location_of_parking__c IS NULL THEN '-'  ELSE b.location_of_parking__c END AS location_of_parking__c,  "
				 
				+ " CASE WHEN b.parking_area_sq_ft__c IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(b.parking_area_sq_ft__c as numeric (20,2))  END AS parking_area_sq_ft__c, "
				+ " CASE WHEN b.parking_area_sq_mt__c IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(b.parking_area_sq_mt__c as numeric (20,2))  END AS parking_area_sq_mt__c, "
				+ " CASE WHEN b.dimensions__c IS NULL THEN '-'  ELSE b.dimensions__c END AS dimensions__c  "
				
				+ " FROM salesforce.gpl_cs_hold_admin_parking a  "
				+ " LEFT JOIN salesforce.propstrength__car_parking__c b on a.parkingsfid = b.sfid   "
				+ " LEFT JOIN salesforce.propstrength__property__c c on c.sfid = a.flatsfid "
				+ " LEFT JOIN salesforce.propstrength__tower__c d on d.sfid = c.propstrength__tower__c "
				+ " LEFT JOIN salesforce.propstrength__tower__c e on b.propstrength__tower_code__c = e.sfid "
				+ " LEFT JOIN salesforce.mst_user f on f.user_id = a.created_by   "
				  
				+ " where "+whereCondition+"  and a.hold_reason in ('block', 'temp') and  a.hold_status = true  order by a.created_at desc  ", ParkingReport.class);
				 
		
		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}	