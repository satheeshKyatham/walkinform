package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.EnqOnMapDao;
import com.godrej.properties.model.EnqOnMap;


@Repository("enqOnMapDao")
public class EnqOnMapDaoImpl extends AbstractDao<Integer, EnqOnMap> implements EnqOnMapDao{
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	
	@Override
	public List<EnqOnMap> getEnqDtl(String projectId) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		//Date(created) = now() and
		List<EnqOnMap> list =session.createQuery(" from EnqOnMap where    sfid='"+projectId+"'   and residencelat <> '' and residencelng <> '' ").list();
		
		if(list.size()>0)
			return list;
		return null;
	}
}