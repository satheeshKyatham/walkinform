package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.InventoryDtlDao;
import com.godrej.properties.model.ApplicationDtl;
import com.godrej.properties.model.EnqOnMap;
import com.godrej.properties.model.InventoryDtl; 

@Repository("inventoryDtlDao")
public class InventoryDtlDaoImpl extends AbstractDao<Integer, InventoryDtl> implements InventoryDtlDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	/*@Override
	public List<InventoryDtl> getHouseUnit(String project_code, String tower_code, String floor_code, String unit) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		List<InventoryDtl> list =session.createQuery("Select Distinct(propstrength__house_unit_no__c)  as propstrength__house_unit_no__c,tower_code__c,sfid,propstrength__property_name__c  "
				+ "FROM  InventoryDtl  where  propstrength__project_name__c='"+project_code+"'  and tower_code__c='"+tower_code+"'  "
				+ " AND propStrength__active__c = true AND "
				+ " propStrength__allotted__c = false AND "
				+ " propstrength__property_on_hold_for_reallocation__c = false AND "
				+ " propstrength__property_alloted_through_offer__c = false " ).list();
		
		if(list.size()>0)
			return list;
		return null;
	}*/
	
	
	@Override
	public List<InventoryDtl> getHouseUnit(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<InventoryDtl> authors=null;
		
		Query q = session.createNativeQuery("Select *  "
				+ " FROM  salesforce.vw_inventory_dtl where "+whereCondition+" "
				+ " AND propStrength__active__c = true AND"
				+ " propStrength__allotted__c = false AND "
				+ " propstrength__property_on_hold_for_reallocation__c = false AND"
				+ " propstrength__property_alloted_through_offer__c = false "
				+ " order by floor_number__c, propstrength__house_unit_no__c, wing_block__c ASC", InventoryDtl.class);

		
		
		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
	
	
	
	/*
	@Override
	public List<InventoryDtl> getHouseUnit(String project_code, String tower_code, String floor_code, String unit) {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<InventoryDtl>  list  ;
	 	if(floor_code!=null && !"".equals(floor_code) && unit!=null &&!"".equals(unit) ) {
			list  = session.createQuery("Select Distinct(propstrength__house_unit_no__c)  as propstrength__house_unit_no__c,tower_code__c,sfid,propstrength__property_name__c  "
					+ "FROM  InventoryDtl  where  propstrength__project_name__c='"+project_code+"'  and tower_code__c='"+tower_code+"' and propstrength__floor_number__c='"+floor_code+"'  and PropStrength__Unit_Type__c='"+unit+"'"
							+ " AND propStrength__active__c = true AND "
							+ " propStrength__allotted__c = false AND  "
							+ " propstrength__property_on_hold_for_reallocation__c = false AND "
							+ " propstrength__property_alloted_through_offer__c = false " ).list();
	 	}else {
	 		list  = session.createQuery("Select Distinct(propstrength__house_unit_no__c)  as propstrength__house_unit_no__c,tower_code__c,sfid,propstrength__property_name__c  "
					+ "FROM  InventoryDtl  where  propstrength__project_name__c='"+project_code+"'  and tower_code__c='"+tower_code+"'  "
							+ " AND propStrength__active__c = true AND "
							+ " propStrength__allotted__c = false AND "
							+ " propstrength__property_on_hold_for_reallocation__c = false AND "
							+ " propstrength__property_alloted_through_offer__c = false " ).list();
	 	}
	 	
		if(list.size()>0)
		{
			  return list;
		}
		return null;
	}*/
}