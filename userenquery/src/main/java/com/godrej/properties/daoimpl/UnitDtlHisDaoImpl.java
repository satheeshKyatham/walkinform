package com.godrej.properties.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.UnitDtlDao;
import com.godrej.properties.dao.UnitDtlHisDao;
import com.godrej.properties.model.UnitDtlHis;

@Repository("unitDtlHisDao")
public class UnitDtlHisDaoImpl extends AbstractDao<Integer, UnitDtlHis> implements UnitDtlHisDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public void setUnitDtl(UnitDtlHis unitDtlHis) {
		persist(unitDtlHis);
	}	
}
