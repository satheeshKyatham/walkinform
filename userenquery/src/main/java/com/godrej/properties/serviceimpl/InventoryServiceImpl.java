package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.InventoryAdminDao;
import com.godrej.properties.dao.InventoryDao;
import com.godrej.properties.dao.PaymentPlanDao;
import com.godrej.properties.dto.HoldInventoryAdminDao;
import com.godrej.properties.dto.HoldInventoryAdminLogDao;
import com.godrej.properties.model.HoldInventoryAdmin;
import com.godrej.properties.model.HoldInventoryAdminLog;
import com.godrej.properties.model.Inventory;
import com.godrej.properties.model.InventoryAdmin;
import com.godrej.properties.model.PaymentPlan;
import com.godrej.properties.service.InventoryService;
import com.godrej.properties.service.PaymentPlanService;

@Service("inventoryService")
@Transactional
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	InventoryDao inventoryDao;
	
	@Autowired
	InventoryAdminDao inventoryAdminDao;
	
	@Autowired
	HoldInventoryAdminDao holdInventoryAdminDao;
	
	@Autowired
	HoldInventoryAdminLogDao holdInventoryAdminLogDao;
	
	@Override
	public List<Inventory> getUnitDtl(String project_code, String towerMst, String typoMst, String holdMst, String soldMst,String facing, String unitAvailable) {
		// TODO Auto-generated method stub
		return inventoryDao.getUnitDtl(project_code, towerMst, typoMst, holdMst, soldMst,facing, unitAvailable);
	}
			
	@Override
	public List<InventoryAdmin> getUnitDtlAdmin(String projectId, String towerMst, String typoMst, String holdMst,
			String soldMst, String unitAvailable,String facing) {
		// TODO Auto-generated method stub
		return inventoryAdminDao.getUnitDtlAdmin(projectId, towerMst, typoMst, holdMst, soldMst ,unitAvailable,facing);
	}

	@Override
	public void saveHoldInventoryAdmin(HoldInventoryAdmin inventoryAdmin) {
		// TODO Auto-generated method stub
		holdInventoryAdminDao.saveHoldInventoryAdmin(inventoryAdmin);
		
		
	}

	@Override
	public void saveHoldInventoryAdminLog(HoldInventoryAdminLog inventoryAdminLog) {
		// TODO Auto-generated method stub
		holdInventoryAdminLogDao.saveHoldInventoryAdminLog(inventoryAdminLog);
	}

	@Override
	public void updateHoldInventoryAdmin(HoldInventoryAdmin inventoryAdmin) {
		// TODO Auto-generated method stub
		holdInventoryAdminDao.updateHoldInventoryAdmin(inventoryAdmin);
	}
	
}
