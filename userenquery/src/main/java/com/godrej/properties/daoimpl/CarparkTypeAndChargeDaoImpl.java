package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CarparkTypeAndChargeDao;
import com.godrej.properties.model.CarparkCount;
import com.godrej.properties.model.CarparkTypeAndCharge;

@Repository("carparkTypeAndChargeDao")
public class CarparkTypeAndChargeDaoImpl extends AbstractDao<Integer, CarparkTypeAndCharge> implements CarparkTypeAndChargeDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<CarparkTypeAndCharge> getCarparkType(String projectSFID) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<CarparkTypeAndCharge> authors=null;
		
		Query q = session.createNativeQuery("SELECT a.id, a.region_name, a.project_name, a.project_id, a.carpark_type, a.isactive, a.createdby, a.updatedby  "
				+ " FROM salesforce.gpl_cs_carpark_type_mst a "
				+ " INNER JOIN salesforce.gpl_cs_carpark_charges b ON a.id = b.carpark_type_mst_id "
				+ " where a.project_id = '"+projectSFID+"' and a.isactive = 'A'  ", CarparkTypeAndCharge.class);
				//+ " FROM salesforce.gpl_cs_carpark_type_mst where project_id = '"+projectSFID+"' and isactive = 'A'  ", CarparkTypeAndCharge.class);

		authors = q.getResultList();
		
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
	
	
	@Override
	public List<CarparkCount> getCarparkCount(String projectSFID) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<CarparkCount> authors=null;
		
		Query q = session.createNativeQuery("SELECT row_number() OVER () AS id, "
				
				+ " CASE WHEN y.total_carpark IS NULL THEN 0 ELSE y.total_carpark  END AS total_carpark,  "
				//+ " y.total_carpark,  "
				
				+ " y.carpark_alias, "
				+ " y.carpark_type, "
				//+ " x.sold_carpark  "				
				
				+ " CASE WHEN x.sold_carpark IS NULL THEN 0 ELSE x.sold_carpark  END AS sold_carpark  "
				//+ " x.sold_carpark  "
				
				+ " FROM salesforce.gpl_cs_carpark_type_mst y   "
				+ " LEFT JOIN(SELECT  b.carpark_type, a.car_park_type, COUNT (a.car_park_type) as sold_carpark "
				+ " FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.gpl_cs_carpark_type_mst b ON a.car_park_type = b.carpark_type AND b.project_id = '"+projectSFID+"' AND b.isactive = 'A'  "
				+ " INNER JOIN salesforce.propstrength__offer__c f ON a.offer_sfid = f.sfid AND f.propstrength__status__c	= 'Closed Won'"
				+ " where a.project_sfid='"+projectSFID+"'   "
				+ " and a.isactive='A' group by a.car_park_type, b.carpark_type) x ON x.carpark_type = y.carpark_type where  y.project_id = '"+projectSFID+"' ", CarparkCount.class);

		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
	
}