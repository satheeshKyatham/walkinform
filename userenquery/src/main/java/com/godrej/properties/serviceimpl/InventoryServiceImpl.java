package com.godrej.properties.serviceimpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.InventoryAdminDao;
import com.godrej.properties.dao.InventoryDao;
import com.godrej.properties.dto.HoldInventoryAdminDao;
import com.godrej.properties.dto.HoldInventoryAdminLogDao;
import com.godrej.properties.model.HoldInventoryAdmin;
import com.godrej.properties.model.HoldInventoryAdminLog;
import com.godrej.properties.model.Inventory;
import com.godrej.properties.model.InventoryAdmin;
import com.godrej.properties.service.InventoryService;
import com.godrej.properties.webservices.DrupalInventoryStatusUpdate;

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
	
	@Autowired
	DrupalInventoryStatusUpdate drupalInventoryStatusUpdate;
	
	
	@Override
	public List<Inventory> getUnitDtl(String project_code, String towerMst, String typoMst, String holdMst, String soldMst,String facing, String unitAvailable, String unitCategory) {
		return inventoryDao.getUnitDtl(project_code, towerMst, typoMst, holdMst, soldMst,facing, unitAvailable, unitCategory);
	}
			
	@Override
	public List<InventoryAdmin> getUnitDtlAdmin(String projectId, String towerMst, String typoMst, String holdMst,
			String soldMst, String unitAvailable,String facing, String unitCategory) {
		return inventoryAdminDao.getUnitDtlAdmin(projectId, towerMst, typoMst, holdMst, soldMst ,unitAvailable,facing, unitCategory);
	}

	//@Override
	public void saveHoldInventoryAdmin(HoldInventoryAdmin inventoryAdmin) {
		holdInventoryAdminDao.saveHoldInventoryAdmin(inventoryAdmin);
	}

	@Override
	public void saveHoldInventoryAdminLog(HoldInventoryAdminLog inventoryAdminLog) {
		holdInventoryAdminLogDao.saveHoldInventoryAdminLog(inventoryAdminLog);
	}

	@Override
	public void updateHoldInventoryAdmin(HoldInventoryAdmin inventoryAdmin) {
		holdInventoryAdminDao.updateHoldInventoryAdmin(inventoryAdmin);
	}
	
	
	@Override
	public void releaseInventory(String projectSfid, String unitSfid, String userId, String unitName) {
	
		HoldInventoryAdmin inventoryAdmin = new HoldInventoryAdmin();
		inventoryAdmin.setUnitSfid(unitSfid);
		inventoryAdmin.setProject_id(projectSfid);
		inventoryAdmin.setCreated_at(new Timestamp(new Date().getTime()));
		inventoryAdmin.setCustomer_id(userId);
		inventoryAdmin.setHold_reason("Release Admin");
		inventoryAdmin.setHold_status(false);
		inventoryAdmin.setEoi_unit_locked(false);

		updateHoldInventoryAdmin(inventoryAdmin);

		// -------------------------------------
		HoldInventoryAdminLog inventoryAdminLog = new HoldInventoryAdminLog();
		inventoryAdminLog.setUnitSfid(unitSfid);
		inventoryAdminLog.setProject_id(projectSfid);
		inventoryAdminLog.setCreated_at(new Timestamp(new Date().getTime()));
		inventoryAdminLog.setCustomer_id(userId);
		inventoryAdminLog.setHold_reason("Release Admin");
		inventoryAdminLog.setHold_status(false);
		inventoryAdminLog.setEoi_unit_locked(false);

		saveHoldInventoryAdminLog(inventoryAdminLog);

	}
	
	/* This service used for GPLAPPS EOI_BLOCK functionality as well as D4U Admin Inventory
	 * Class Name : GPLAppsWebServiceImpl.java*/ 
	@Override
	public void holdInventoryAdmin(String projectId, String userId, String unitsfid, String holdmsg, String reasonInput, String holdBlockBehalfOfName, int holdBlockBehalfOfID, String  enqSFID)
	{
		boolean eoiHoldStatus = false;	
		
		if (holdmsg.equals("eoi_block")) {
			eoiHoldStatus = true;
			holdmsg = "temp";
		} else {
			eoiHoldStatus = false;
		}
			
		 String [] data= unitsfid.split(",");
		 
		 StringBuilder successUnitUpdate = new StringBuilder();
		 String drupalUpdateUnit = "";
		 
		 for (int i=0;i<data.length;i++){
			 HoldInventoryAdmin inventoryAdmin= new HoldInventoryAdmin();
			 inventoryAdmin.setUnitSfid(data[i]);
			 inventoryAdmin.setProject_id(projectId);
			 inventoryAdmin.setCreated_at(new Timestamp(new Date ().getTime()));
			 inventoryAdmin.setCustomer_id(userId);
			 inventoryAdmin.setHold_reason(holdmsg);
			 inventoryAdmin.setHold_status(true);
			 
			 inventoryAdmin.setHold_description(reasonInput);
			 inventoryAdmin.setHold_behalf_username(holdBlockBehalfOfName);
			 inventoryAdmin.setHold_behalf_userid(holdBlockBehalfOfID);
			 
			 inventoryAdmin.setEoi_unit_locked(eoiHoldStatus);
			 inventoryAdmin.setEnq_sfid(enqSFID);
			 
			 saveHoldInventoryAdmin(inventoryAdmin);
			 //-------------------------
			 HoldInventoryAdminLog inventoryAdminLog= new HoldInventoryAdminLog();
			 inventoryAdminLog.setUnitSfid(data[i]);
			 inventoryAdminLog.setProject_id(projectId);
			 inventoryAdminLog.setCreated_at(new Timestamp(new Date ().getTime()));
			 inventoryAdminLog.setCustomer_id(userId);
			 inventoryAdminLog.setHold_reason(holdmsg);
			 inventoryAdminLog.setHold_status(true);
			 
			 inventoryAdminLog.setHold_description(reasonInput);
			 inventoryAdminLog.setHold_behalf_username(holdBlockBehalfOfName);
			 inventoryAdminLog.setHold_behalf_userid(holdBlockBehalfOfID);
			 inventoryAdminLog.setEoi_unit_locked(eoiHoldStatus);
			 inventoryAdminLog.setEnq_sfid(enqSFID);
			 
			 saveHoldInventoryAdminLog(inventoryAdminLog);
			 
			 successUnitUpdate.append(data[i]);
			 successUnitUpdate.append("_");
		 } 
		 
		 drupalUpdateUnit = successUnitUpdate.toString();
		 if (drupalUpdateUnit != null && drupalUpdateUnit.length() > 0 && drupalUpdateUnit.charAt(drupalUpdateUnit.length() - 1) == '_') {
			 drupalUpdateUnit = drupalUpdateUnit.substring(0, drupalUpdateUnit.length() - 1);
		 }
		 
		 //if (holdmsg.equals("block")) {
		drupalInventoryStatusUpdate.inventoryStatusUpdate(drupalUpdateUnit, "true");
		 //}
		 
		 
	}
	
	
	/* New Method for release EOI Unit Hold */
	@Override
	public void releaseEOIHoldInventory(String projectSfid, String unitSfid, String userId, String unitName) {
		
		 String [] data= unitSfid.split(",");
		// String [] units= unitName.split(",");
		 
		 for (int i=0;i<data.length;i++){
			 	HoldInventoryAdmin inventoryAdmin = new HoldInventoryAdmin();
				inventoryAdmin.setUnitSfid(data[i]);
				inventoryAdmin.setProject_id(projectSfid);
				inventoryAdmin.setCreated_at(new Timestamp(new Date().getTime()));
				inventoryAdmin.setCustomer_id(userId);
				inventoryAdmin.setHold_reason("Release Admin");
				inventoryAdmin.setHold_status(false);
				inventoryAdmin.setEoi_unit_locked(false);

				updateHoldInventoryAdmin(inventoryAdmin);

				// -------------------------------------
				HoldInventoryAdminLog inventoryAdminLog = new HoldInventoryAdminLog();
				inventoryAdminLog.setUnitSfid(data[i]);
				inventoryAdminLog.setProject_id(projectSfid);
				inventoryAdminLog.setCreated_at(new Timestamp(new Date().getTime()));
				inventoryAdminLog.setCustomer_id(userId);
				inventoryAdminLog.setHold_reason("Release Admin");
				inventoryAdminLog.setHold_status(false);
				inventoryAdminLog.setEoi_unit_locked(false);

				saveHoldInventoryAdminLog(inventoryAdminLog);
		}
	}
	/* END New Method for release EOI Unit Hold */
	
}