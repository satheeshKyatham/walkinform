package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.Inventory;

public interface InventoryDao {

	List<Inventory> getUnitDtl(String project_code, String towerMst, String typoMst, String holdMst, String soldMst,String facing, String unitAvailable, String unitCategory);

}
