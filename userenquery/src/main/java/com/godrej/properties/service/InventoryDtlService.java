package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.InventoryDtl;

public interface InventoryDtlService {
	List<InventoryDtl> getHouseUnit(String whereCondition);
}