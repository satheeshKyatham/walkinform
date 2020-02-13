package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.InventoryOtherChargesDao;
import com.godrej.properties.model.InventoryOtherCharges;

@Repository("inventoryOtherChargesDao")
public class InventoryOtherChargesDaoImpl extends AbstractDao<Integer, InventoryOtherCharges> implements InventoryOtherChargesDao{
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public List<InventoryOtherCharges> getOtherCharge(String unitSFID) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<InventoryOtherCharges> authors=null;
		
		
		Query q = session.createNativeQuery("SELECT * FROM salesforce.vw_inventory_other_charges "
				
				+ " where propstrength__property__c = '"+unitSFID+"' and isdeleted != true and propstrength__active__c != false  ", InventoryOtherCharges.class);
		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}
