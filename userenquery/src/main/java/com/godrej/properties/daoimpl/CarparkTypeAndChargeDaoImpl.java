package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CarparkTypeAndChargeDao;
import com.godrej.properties.model.CarparkTypeAndCharge;

@Repository("carparkTypeAndChargeDao")
public class CarparkTypeAndChargeDaoImpl extends AbstractDao<Integer, CarparkTypeAndCharge> implements CarparkTypeAndChargeDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<CarparkTypeAndCharge> getCarparkType(String projectSFID) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<CarparkTypeAndCharge> authors=null;
		
		Query q = session.createNativeQuery("SELECT a.id, a.region_name, a.project_name, a.project_id, a.carpark_type, a.isactive, a.createdby, a.updatedby  "
				+ " FROM salesforce.gpl_cs_carpark_type_mst a "
				+ " INNER JOIN salesforce.gpl_cs_carpark_charges b ON a.id = b.carpark_type_mst_id "
				+ " where a.project_id = '"+projectSFID+"' and a.isactive = 'A'  ", CarparkTypeAndCharge.class);
				//+ " FROM salesforce.gpl_cs_carpark_type_mst where project_id = '"+projectSFID+"' and isactive = 'A'  ", CarparkTypeAndCharge.class);

		authors = q.getResultList();
		
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
	
	
}
