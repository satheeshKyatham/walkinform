package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.TowerPPExclusion;


public interface TowerPPExclusionService {

	public TowerPPExclusion addTowerPPExclusion(TowerPPExclusion data);

	public List<TowerPPExclusion> getTowerPPExclusiondData();

	public boolean deleteTowerPPExclusionRecord(int id);

	public boolean getTowerPP(TowerPPExclusion data);

}
