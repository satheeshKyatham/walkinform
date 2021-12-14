package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CarparkTypeAndChargeDao;
import com.godrej.properties.model.CarParkingMapping;
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

		return authors;
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

		return authors;
	}


	@Override
	public List<CarParkingMapping> getCarParkingCombination(String towerSFID) {
Session session = this.sessionFactory.getCurrentSession();	
		
		List<CarParkingMapping> carChargesMapping=null;
		
		/*Query q = session.createNativeQuery("select row_number() OVER () AS id,p.propstrength__unit_type__c as property_type_name,c.propstrength__category_of_parking__c as parking_category,p.PropStrength__Tower__c as tower_sfid,p.PropStrength__project_name__c as project_sfid,0 as absolute_amount, now() as created,0 as createdby,now() as updated,0 as updatedby, '' as isactive"
				+ " ,pt.sfid as property_type_sfid,pt.PropStrength__Property_Type_Code__c as property_type_code"
				+ " from salesforce.PropStrength__Property__c p  "
				+ " inner join salesforce.PropStrength__Property_Type__c pt on(p.PropStrength__Property_Type__c=pt.sfid)"
				+ " inner join salesforce.propstrength__car_parking__c c on(p.sfid=c.PropStrength__property_name__c) where p.PropStrength__Tower__c='"+towerSFID+"'"
				+ " and c.propstrength__category_of_parking__c is not null group by p.propstrength__unit_type__c,c.propstrength__category_of_parking__c,p.PropStrength__Tower__c,p.PropStrength__project_name__c,pt.sfid,pt.PropStrength__Property_Type_Code__c order by p.propstrength__unit_type__c ", CarParkingMapping.class);*/
		
		Query q = session.createNativeQuery("select row_number() OVER () AS id,p.propstrength__unit_type__c as property_type_name,c.propstrength__category_of_parking__c as parking_category,p.PropStrength__Tower__c as tower_sfid,p.PropStrength__project_name__c as project_sfid, now() as created,0 as createdby,now() as updated,0 as updatedby, pmapping.isactive as isactive"
				+ " ,pt.sfid as property_type_sfid"
				+ " ,(CASE WHEN pmapping.property_type_sfid is null THEN pt.sfid ELSE pmapping.property_type_sfid END) property_type_sfid"
				+ " ,(CASE WHEN pmapping.absolute_amount=0 THEN 0 ELSE pmapping.absolute_amount END) absolute_amount"
				+ " ,(CASE WHEN pmapping.property_type_code is null THEN pt.PropStrength__Property_Type_Code__c ELSE pmapping.property_type_code END) property_type_code"
				+ " ,(CASE WHEN pmapping.absolute_amount is not null THEN pmapping.absolute_amount ELSE null END)absolute_amount"
				+ " from salesforce.PropStrength__Property__c p  "
				+ " inner join salesforce.PropStrength__Property_Type__c pt on(p.PropStrength__Property_Type__c=pt.sfid)"
//				+ " left outer join salesforce.propstrength__car_parking__c c on(p.sfid=c.PropStrength__property_name__c) "
				+ " CROSS JOIN salesforce.propstrength__car_parking__c c "
				+ " left join salesforce.nv_parking_mapping pmapping on(pt.sfid=pmapping.property_type_sfid and c.propstrength__category_of_parking__c=pmapping.parking_category and pt.PropStrength__Property_Type_Code__c=pmapping.property_type_code and tower_sfid='"+towerSFID+"')"
				+ " where p.PropStrength__Tower__c='"+towerSFID+"' and c.PropStrength__tower_code__c='"+towerSFID+"'"
				+ "  group by p.propstrength__unit_type__c,c.propstrength__category_of_parking__c,p.PropStrength__Tower__c,p.PropStrength__project_name__c,pt.sfid,pt.PropStrength__Property_Type_Code__c,pmapping.property_type_sfid,pmapping.absolute_amount,pmapping.property_type_code,pmapping.isactive order by p.propstrength__unit_type__c ", CarParkingMapping.class);
//and c.propstrength__category_of_parking__c is not null
		carChargesMapping = q.getResultList();
		if(carChargesMapping.size() > 0)
			return carChargesMapping;
		else
			return carChargesMapping;
	}
}