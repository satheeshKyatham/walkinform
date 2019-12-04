package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.CostSheetHisDao;
import com.godrej.properties.model.CostSheetHis;
import com.godrej.properties.service.CostSheetHisService;

@Service("costSheetHisService")
@Transactional
public class CostSheetHisServiceImpl implements CostSheetHisService{
	
	@Autowired
	private CostSheetHisDao dao;
	
	
	@Override
	public void setCostSheet(List<CostSheetHis> costSheetsHis, String equery_id) {
		dao.setCostSheet(costSheetsHis, equery_id);
		
	}
	
	

}
