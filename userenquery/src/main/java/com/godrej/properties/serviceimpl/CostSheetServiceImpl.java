package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.CostSheetDao;
import com.godrej.properties.model.CostSheet;
import com.godrej.properties.service.CostSheetService;

@Service("costSheetService")
@Transactional
public class CostSheetServiceImpl implements CostSheetService{
	
	@Autowired
	private CostSheetDao dao;
	
	
	@Override
	public void setCostSheet(List<CostSheet> costSheets, String equery_id) {
		dao.setCostSheet(costSheets, equery_id);
		
	}
	
	

}
