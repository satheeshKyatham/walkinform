package com.godrej.properties.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.UnitDtlDao;
import com.godrej.properties.model.UnitDtl;

@Repository("unitDtlDao")
public class UnitDtlDaoImpl extends AbstractDao<Integer, UnitDtl> implements UnitDtlDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public void setUnitDtl(UnitDtl unitDtl) {
		persist(unitDtl);
	}	
}
