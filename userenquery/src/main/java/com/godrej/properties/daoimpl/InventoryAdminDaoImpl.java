package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.InventoryAdminDao;
import com.godrej.properties.model.Inventory;
import com.godrej.properties.model.InventoryAdmin;

@Repository("inventoryAdminDao")
public class InventoryAdminDaoImpl extends AbstractDao<Integer, InventoryAdmin> implements InventoryAdminDao{

	@Autowired
	private SessionFactory sessionFactory;	
	
	@SuppressWarnings("unchecked")
	
	public List<InventoryAdmin> getUnitDtlAdmin(String project_code, String towerMst, String typoMst, String holdMst, String soldMst,String unitAvailable,String facing) {	 
		
		Session session = this.sessionFactory.getCurrentSession();	
		
		//typologyAll
		
		String typology = "";
		
		if (typoMst.equals("typologyAll")) {
			typology = "";
		} else {
			typology = "and propstrength__unit_type__c = '"+typoMst+"'";
		}
		 String unitAvailble="";
		if (unitAvailable.equals("all")) {
			unitAvailble = "";
		} else if(unitAvailable.equals("f")) {
			unitAvailble = "and propstrength__allotted__c = '"+unitAvailable+"'";
		}else if (unitAvailable.equals("t")) {
			unitAvailble = "and hold_status = 't'";
		}
		
		List<InventoryAdmin> list =session.createQuery(" FROM  InventoryAdmin  where propstrength__project_name__c='"+project_code+"' and  tower_code__c='"+towerMst+"' "
				+unitAvailble + typology +" and propstrength__active__c='t' ORDER BY floor_number__c, propstrength__house_unit_no__c, wing_block__c").list();
		
		if(list.size()>0)
			return list;
		
		return null ;
		
	}
}	