package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.OldNewValueDao;
import com.godrej.properties.model.ValueOldNew;

@Repository("oldNewValueDao")
public class OldNewValueDaoImpl extends AbstractDao<Integer, ValueOldNew> implements OldNewValueDao  {
	@Autowired
	private SessionFactory sessionFactory;
	public OldNewValueDaoImpl() {
		
	}
	@Override
	public List<ValueOldNew> old_newValueDataSysnc() {
		Session session = this.sessionFactory.getCurrentSession();
		List<ValueOldNew> list  =session.createQuery("  FROM  ValueOldNew ").list(); 
		if(list.size()>0)
		{
			return list;
		}
		return list;
	}

	//List<ValueOldNew> old_newValueDataSysnc();

}
