package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.CountryDao;
import com.godrej.properties.model.Country;

@Repository
//@Repository("vw_UserMasterDao")
@Transactional
public class CountryDaoImpl implements CountryDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Country getCountryData(String countryCode) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Country> list =null;
		 list =session.createQuery(" from Country where  code = '"+countryCode+"'").list();
	 
		if(list.size()>0)
			return list.get(0);
		return null;
	}

}
