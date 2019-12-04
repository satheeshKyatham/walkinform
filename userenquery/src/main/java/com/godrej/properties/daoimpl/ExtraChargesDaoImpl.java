package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.ExtraChargesDao;
import com.godrej.properties.model.ExtraCharges;

@Repository("extraChargesDao")
public class ExtraChargesDaoImpl extends AbstractDao<Integer, ExtraCharges> implements ExtraChargesDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void setExtraChrgs(List<ExtraCharges> charges, String equery_id, String contactNo) {
		
		/*@SuppressWarnings("rawtypes")
		Query query =   getSession().createQuery("update CostSheet set isactive= :isactive " 
					+  " where user_contact = :user_contact");
		query.setParameter("isactive", "N");
		query.setParameter("user_contact",contactNo);
		
		query.executeUpdate();*/
		
		
		batchPersist(charges);
	}	

}
