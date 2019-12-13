package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CarparkTypeMstDao;
import com.godrej.properties.model.CarparkTypeMst;
import com.godrej.properties.model.SchemePromotional;

@Repository("carparkTypeMstDao")
public class CarparkTypeMstDaoImpl extends AbstractDao<Integer, CarparkTypeMst> implements CarparkTypeMstDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertCarparkDtl(CarparkTypeMst carparkTypeMst) {
		persist(carparkTypeMst);
	}
	
	@Override
	public List<CarparkTypeMst> getCarparkType(String projectSFID) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<CarparkTypeMst> authors=null;
		
		Query q = session.createNativeQuery("SELECT id, region_name, project_name, project_id, carpark_type, isactive, createdby, updatedby  "
				+ " FROM salesforce.gpl_cs_carpark_type_mst where project_id = '"+projectSFID+"' and isactive = 'A'  ", CarparkTypeMst.class);

		authors = q.getResultList();
		
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
	
	
}
