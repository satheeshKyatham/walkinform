package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.InventoryDtlDao;
import com.godrej.properties.model.InventoryDtl;
import com.godrej.properties.service.InventoryDtlService;

@Service("inventoryDtlService")
@Transactional
public class InventoryDtlServiceImpl implements InventoryDtlService {

	@Autowired
	InventoryDtlDao inventoryDtlDao;
	
	@Override
	public List<InventoryDtl> getHouseUnit(String whereCondition) {
		return inventoryDtlDao.getHouseUnit(whereCondition);
	}
}