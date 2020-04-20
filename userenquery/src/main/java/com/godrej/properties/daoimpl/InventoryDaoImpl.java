package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.InventoryDao;
import com.godrej.properties.dao.PaymentPlanListDao;
import com.godrej.properties.model.Inventory;
import com.godrej.properties.model.PaymentPlanList;
import com.godrej.properties.model.RequestAction;
import com.godrej.properties.model.ZzholdTest;

@Repository("inventoryDao")
public class InventoryDaoImpl extends AbstractDao<Integer, Inventory> implements InventoryDao{

	@Autowired
	private SessionFactory sessionFactory;	
	
	@SuppressWarnings("unchecked")
	
	public List<Inventory> getUnitDtl(String project_code, String towerMst, String typoMst, String holdMst, String soldMst,String facing, String unitAvailable) {	 
		
		Session session = this.sessionFactory.getCurrentSession();	
		
		//typologyAll
		
		String typology = "";
		String selectedFacing = "";
		String unitAvailbleCondition="";
		
		if (typoMst.equals("typologyAll")) {
			typology = "";
		} else {
			typology = "and propstrength__unit_type__c = '"+typoMst+"'";
		}
		if (facing.equals("0")) {
			selectedFacing = "";
		} else {
			selectedFacing = "and Property_Facing__c = '"+facing+"'";
		}
		
		if (unitAvailable.equals("f")) {
			
			unitAvailbleCondition = " and propstrength__allotted__c = '"+unitAvailable+"'  and  PropStrength__Property_Alloted_Through_Offer__c = 'f'  and  propstrength__property_on_hold_for_reallocation__c = 'f' ";
		
		}
		
		List<Inventory> list =session.createQuery(" FROM  Inventory  where propstrength__project_name__c='"+project_code+"' and  tower_code__c='"+towerMst+"' "+ typology +" "+selectedFacing+"  "+unitAvailbleCondition+"  and propstrength__active__c='t' ORDER BY floor_number__c, propstrength__house_unit_no__c, wing_block__c").list();
		
		if(list.size()>0)
			return list;
		
		return null ;
	}
}	