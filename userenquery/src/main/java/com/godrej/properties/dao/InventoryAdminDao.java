package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.InventoryAdmin;

public interface InventoryAdminDao {

	List<InventoryAdmin> getUnitDtlAdmin(String projectId, String towerMst, String typoMst, String holdMst, String soldMst,
			String unitAvailable,String facing);

}
