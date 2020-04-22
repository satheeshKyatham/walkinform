package com.godrej.properties.serviceimpl;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.TowerPPExclusionDao;

import com.godrej.properties.model.TowerPPExclusion;
import com.godrej.properties.service.TowerPPExclusionService;

@Service("towerPPExclusionService")
@Transactional
public class TowerPPExclusionServiceImpl implements TowerPPExclusionService{

	@Autowired
	private TowerPPExclusionDao towerPPExclusionDao;
	@Override
	public TowerPPExclusion addTowerPPExclusion(TowerPPExclusion data) {
		TowerPPExclusion towerPPData=new TowerPPExclusion();
		towerPPData.setIsactive("A");
		towerPPData.setProject_sfid(data.getPayment_plan_sfid());;
		towerPPData.setProject_name(data.getProject_name());
		towerPPData.setPayment_plan_sfid(data.getPayment_plan_sfid());
		towerPPData.setPayment_plan_name(data.getPayment_plan_name());
		towerPPData.setCreatedby(9999);
		towerPPData.setTower_sfid(data.getTower_sfid());
		towerPPData.setTower_name(data.getTower_name());	
		Timestamp createdTimestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(createdTimestamp);
        towerPPData.setCreated(createdTimestamp);
        towerPPData=towerPPExclusionDao.insertTowerPPExclusion(towerPPData);
		return towerPPData;
	}
	@Override
	public List<TowerPPExclusion> getTowerPPExclusiondData() {
		
		return towerPPExclusionDao.getTowerPPExclusionQuery();
	}
	@Override
	public boolean deleteTowerPPExclusionRecord(int id) {
		
		return towerPPExclusionDao.deleteTowerPPExclusionRecordQuery(id);
	}

}
