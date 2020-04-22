package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.TowerPPExclusion;

public interface TowerPPExclusionDao {

	public TowerPPExclusion insertTowerPPExclusion(TowerPPExclusion towerPPData);

	public List<TowerPPExclusion> getTowerPPExclusionQuery();

	public boolean deleteTowerPPExclusionRecordQuery(int id);

}
