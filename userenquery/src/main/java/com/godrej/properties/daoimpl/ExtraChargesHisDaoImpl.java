package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.ExtraChargesHisDao;
import com.godrej.properties.model.ExtraChargesHis;

@Repository("extraChargesHisDao")
public class ExtraChargesHisDaoImpl extends AbstractDao<Integer, ExtraChargesHis> implements ExtraChargesHisDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void setExtraChrgs(List<ExtraChargesHis> charges, String equery_id, String contactNo) {
		
		/*@SuppressWarnings("rawtypes")*/
		/*Query query =   getSession().createQuery("update CostSheet set isactive= :isactive " 
					+  " where user_contact = :user_contact");
		query.setParameter("isactive", "N");
		query.setParameter("user_contact",contactNo);
		
		query.executeUpdate();*/
		
		
		batchPersist(charges);
	}	

}
