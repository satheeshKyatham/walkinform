package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CarParkChargesDao;
import com.godrej.properties.model.CarParkCharges;
import com.godrej.properties.model.OTPRequestOC;

@SuppressWarnings("unchecked")
@Repository("carParkChargesDao")
public class CarParkChargesDaoImpl extends AbstractDao<Integer, CarParkCharges> implements CarParkChargesDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insertCPAmount(CarParkCharges carParkCharges) {
		persist(carParkCharges);
	}	
	
	
	public CarParkCharges getCharges(String parkType, String projectSFID) {	 
		
		Session session = this.sessionFactory.getCurrentSession();
		List<CarParkCharges> list  =session.createQuery(" FROM  CarParkCharges where project_id = '"+projectSFID+"' and isactive = 'A' and park_type = '"+parkType+"' ").list();
		
		if(list.size()>0)
		{
			return list.get(0);
		}
		
		return null;		
	}
	
}
