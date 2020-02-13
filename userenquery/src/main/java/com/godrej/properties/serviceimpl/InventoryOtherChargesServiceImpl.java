package com.godrej.properties.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.InventoryOtherChargesDao;
import com.godrej.properties.model.InventoryOtherCharges;
import com.godrej.properties.service.InventoryOtherChargesService;

@Service("inventoryOtherChargesService")
@Transactional
public class InventoryOtherChargesServiceImpl implements InventoryOtherChargesService{
	@Autowired
	InventoryOtherChargesDao inventoryOtherChargesDao;
	
	@Override
	public List<InventoryOtherCharges> getOtherCharge(String unitSFID) {
		return inventoryOtherChargesDao.getOtherCharge(unitSFID);
	}
	
	private Map<String, InventoryOtherCharges> getMappedCharges(List<InventoryOtherCharges> otherCharges){
		
		Map<String, InventoryOtherCharges> otherChargesMap =  new HashMap<>();
		
		if(otherCharges == null || otherCharges.isEmpty()) {
			return otherChargesMap;
		}
		for(InventoryOtherCharges charge: otherCharges) {
			String name = charge.getName();
			if(name !=null) {
				name = name.replace(" ", "_");
			}
			otherChargesMap.put(name, charge);
		}
		return otherChargesMap;
	}

	@Override
	public Map<String, InventoryOtherCharges> getOtherChargeMap(String unitSFID) {
		List<InventoryOtherCharges> otherCharges = inventoryOtherChargesDao.getOtherCharge(unitSFID);
		return getMappedCharges(otherCharges);
	}
}