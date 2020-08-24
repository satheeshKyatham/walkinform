package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.TnCDao;
import com.godrej.properties.model.TnC;
import com.godrej.properties.service.TnCService;

@Service("tnCService")
@Transactional
public class TnCServiceImpl implements TnCService{
	
	@Autowired
	private TnCDao dao;
	
	public void insertTNCForPP(TnC tnC) {
		dao.insertTNCForPP(tnC);
	}
	
	
	@Override
	public List<TnC> getTncData(String ppId, String projectid, String tower_sfid) {
		// TODO Auto-generated method stub
		return dao.getTncData(ppId, projectid, tower_sfid);
	}


	@Override
	public boolean deleteTncData(int id) {
		
		return dao.deleteTncQuery(id);
	}


	@Override
	public List<TnC> getSalesTncData(String pymt_plan_id, String project_id, String tower_sfid) {
		return dao.getSalesTncDataQuery(pymt_plan_id, project_id, tower_sfid);
	}


	@Override
	public List<TnC> getSearchTncData(String pymtPlanId, String proId, String towerId) {
		
		return dao.getSearchTncByQuery(pymtPlanId, proId, towerId);
	}
}
