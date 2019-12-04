package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.TowerBand;

public interface TowerBandDao {

	List<TowerBand> getTowerBand(String project_code, String tower_code);

}
