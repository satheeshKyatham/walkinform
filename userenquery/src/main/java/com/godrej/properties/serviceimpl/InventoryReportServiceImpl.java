package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.InventoryReportDao;
import com.godrej.properties.model.InventoryReport;
import com.godrej.properties.service.InventoryReportService;

@Service("inventoryReportService")
@Transactional
public class InventoryReportServiceImpl implements InventoryReportService{
	@Autowired
	InventoryReportDao  inventoryReportDao;

	@Override
	public List<InventoryReport> getInventoryReportDtl(String whereCondition) {
		return inventoryReportDao.getInventoryReportDtl(whereCondition);
	}
}