package com.godrej.properties.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dto.HoldInventoryAdminLogDao;
import com.godrej.properties.model.HoldInventoryAdminLog;

@Repository("holdInventoryAdminLogDao")
public class HoldInventoryAdminLogDaoImpl extends AbstractDao<Integer, HoldInventoryAdminLog> implements HoldInventoryAdminLogDao{

	  
	@Override
	public void saveHoldInventoryAdminLog(HoldInventoryAdminLog inventoryAdminLog) {
		persist(inventoryAdminLog);
		
	}
}	