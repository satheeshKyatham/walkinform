package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.SalesUnitHoldStatusDao;
import com.godrej.properties.model.HoldInventoryEntry;

@Repository("salesUnitHoldStatusDao")
public class SalesUnitHoldStatusDaoImpl extends AbstractDao<Integer, HoldInventoryEntry> implements SalesUnitHoldStatusDao{
	@Autowired
	private SessionFactory sessionFactory;	
	
	
	public Boolean getSalesUnitHold(String unitSFID) {	 
		
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<HoldInventoryEntry> authors=null;
		
		Query q = session.createNativeQuery(" select * from salesforce.gpl_cs_hold_unit_uat "
				+ " where sfid = '"+unitSFID+"' and  statusai = 'A' and holdstatusyn = 'Y' ", HoldInventoryEntry.class);

		authors = q.getResultList();
		
		if (authors.size() > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
}