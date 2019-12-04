package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.BSPUpdateDao;
import com.godrej.properties.dao.ExtraChargesDao;
import com.godrej.properties.model.BSPUpdate;
import com.godrej.properties.model.ExtraCharges;
import com.godrej.properties.model.UnitDtl;


@SuppressWarnings("unchecked")
@Repository("bSPUpdateDao")
public class BSPUpdateDaoImpl extends AbstractDao<Integer, BSPUpdate> implements BSPUpdateDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void updateBSP(UnitDtl bsp) {
		
		
			Session session = this.sessionFactory.getCurrentSession();
			
			
			Query query = session.createQuery("UPDATE UnitDtl SET senttocrm = '"+bsp.getSenttocrm()+"'  WHERE timeid = '"+bsp.getTimeid()+"'");
			
			query.executeUpdate();
			
			//System.out.println("charges ::: " + charges2);
		
	}

	
}
