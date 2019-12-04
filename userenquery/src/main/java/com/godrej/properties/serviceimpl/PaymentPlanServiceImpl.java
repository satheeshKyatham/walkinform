package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.PaymentPlanDao;
import com.godrej.properties.dao.TowerBandDao;
import com.godrej.properties.dao.TowerMasterDao;
import com.godrej.properties.model.PaymentPlan;
import com.godrej.properties.model.TowerBand;
import com.godrej.properties.model.TowerMaster;
import com.godrej.properties.service.PaymentPlanService;

@Service("paymentPlanService")
@Transactional
public class PaymentPlanServiceImpl implements PaymentPlanService {

	@Autowired
	PaymentPlanDao paymentPlanDao;

	@Autowired
	TowerBandDao toweBandDao;

	@Autowired
	TowerMasterDao toweMasterDao;
	
	@Override
	public List<PaymentPlan> getProjectPlan(String project_code, String unit, String towerCode, String pymtPlanSfid) {
		// TODO Auto-generated method stub
		return paymentPlanDao.getProjectPlan(project_code,unit,towerCode, pymtPlanSfid);
	}
	
	@Override
	public List<PaymentPlan> getTower(String project_code) {
		// TODO Auto-generated method stub
		 
		return paymentPlanDao.getTower(project_code);
	}



	@Override
	public List<PaymentPlan> getHouseUnit(String project_code, String tower_code, String floor_code, String unit) {
		// TODO Auto-generated method stub
		return paymentPlanDao.getHouseUnit(project_code,tower_code,floor_code,unit);
	}

	@Override
	public List<PaymentPlan> getUnitType(String project_code, String tower_code, String floor_code) {
		// TODO Auto-generated method stub
		return paymentPlanDao.getUnitType(project_code,tower_code,floor_code);
	}

	@Override
	public List<PaymentPlan> getfloor(String project_code, String tower_code) {
		// TODO Auto-generated method stub
		return paymentPlanDao.getfloor(project_code,tower_code);
	}

	@Override
	public List<TowerBand> getTowerBand(String project_code, String tower_code) {
		// TODO Auto-generated method stub
		return toweBandDao.getTowerBand( project_code,  tower_code);
	}

	@Override
	public List<TowerMaster> getTowerMaster(String project_code) {
		// TODO Auto-generated method stub
		return toweMasterDao.getTowerMaster(project_code);
	}

	 
	
	 
 
 

}
