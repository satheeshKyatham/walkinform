package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.InventoryOtherCharges;

public interface InventoryOtherChargesDao {
	List<InventoryOtherCharges> getOtherCharge(String unitSFID);
}
