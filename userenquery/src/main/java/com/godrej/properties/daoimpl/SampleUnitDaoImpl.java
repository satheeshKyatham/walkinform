package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.SampleUnitDao;
import com.godrej.properties.model.SampleUnit;

@SuppressWarnings("unchecked")
@Repository("sampleUnitDao")
public class SampleUnitDaoImpl extends AbstractDao<Integer, SampleUnit> implements SampleUnitDao{
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public SampleUnit geteoiSampleUnit(String condition) {
		
		Session session = this.sessionFactory.getCurrentSession();
		List<SampleUnit> list  =session.createQuery(" FROM  SampleUnit where "+condition+"  order by id DESC ").list();
		
		if(list.size()>0)
		{
			return list.get(0);
		}
		
		return null;	
	}
}