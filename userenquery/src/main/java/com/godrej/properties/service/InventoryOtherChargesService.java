package com.godrej.properties.service;

import java.util.List;
import java.util.Map;

import com.godrej.properties.model.InventoryOtherCharges;

public interface InventoryOtherChargesService {
	List<InventoryOtherCharges> getOtherCharge(String unitSFID);	
	public Map<String, InventoryOtherCharges> getOtherChargeMap(String unitSFID);
}