package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.InventoryDtl;

public interface InventoryDtlDao {
	List<InventoryDtl> getHouseUnit(String whereCondition);
}