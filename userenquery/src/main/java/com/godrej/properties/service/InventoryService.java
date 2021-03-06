package com.godrej.properties.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.godrej.properties.model.HoldInventoryAdmin;
import com.godrej.properties.model.HoldInventoryAdminLog;
import com.godrej.properties.model.Inventory;
import com.godrej.properties.model.InventoryAdmin;

public interface InventoryService {
	List<Inventory> getUnitDtl(String project_code, String towerMst, String typoMst, String holdMst, String soldMst,String facing, String unitAvailable, String unitCategory);

	List<InventoryAdmin> getUnitDtlAdmin(String projectId, String towerMst, String typoMst, String holdMst, String soldMst,
			String unitAvailable,String facing, String unitCategory);

	//void saveHoldInventoryAdmin(HoldInventoryAdmin inventoryAdmin);

	void saveHoldInventoryAdminLog(HoldInventoryAdminLog inventoryAdminLog);

	void updateHoldInventoryAdmin(HoldInventoryAdmin inventoryAdmin);
	
	
	
	
	void holdInventoryAdmin(String projectId, String userId, String unitsfid, String holdmsg, String reasonInput, String holdBlockBehalfOfName, int holdBlockBehalfOfID, String  enqSFID);

	public void releaseInventory(String projectSfid, String unitSfid, String userId, String unitName);	 
	
	void releaseEOIHoldInventory (String projectSfid, String unitSfid, String userId, String unitName);
	
}
