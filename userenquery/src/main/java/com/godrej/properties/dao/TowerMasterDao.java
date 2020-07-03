package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.TowerMaster;

public interface TowerMasterDao {

	public List<TowerMaster> getTowerMaster(String project_code) ;
	public TowerMaster getTowerMasterDetails(String towersfid) ;

}
