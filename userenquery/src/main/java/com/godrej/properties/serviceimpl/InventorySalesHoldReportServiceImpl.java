package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.HoldInventoryEntryDao;
import com.godrej.properties.dao.InventorySalesHoldReportDao;
import com.godrej.properties.model.InventorySalesHoldReport;
import com.godrej.properties.service.InventorySalesHoldReportService;

@Service("inventorySalesHoldReportService")
@Transactional
public class InventorySalesHoldReportServiceImpl implements InventorySalesHoldReportService{
	@Autowired
	private InventorySalesHoldReportDao dao;
	
	@Autowired
	InventorySalesHoldReportDao inventorySalesHoldReportDao;
	
	@Override
	public List<InventorySalesHoldReport> holdDataExist(String whereCondition) {
		return inventorySalesHoldReportDao.holdDataExist(whereCondition);
	}
}