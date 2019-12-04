package com.godrej.properties.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.OtherChargesDao;
import com.godrej.properties.model.OtherCharges;

@Repository("otherChargesDao")
public class OtherChargesDaoImpl extends AbstractDao<Integer, OtherCharges> implements OtherChargesDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public void insertOtherCharge(OtherCharges otherCharges) {
		persist(otherCharges);
	}	
}
