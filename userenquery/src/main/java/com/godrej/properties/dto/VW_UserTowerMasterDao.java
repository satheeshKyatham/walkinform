package com.godrej.properties.dto;

import java.util.List;

import com.godrej.properties.model.Vw_UserTowerMapping;

public interface VW_UserTowerMasterDao {
	List<Vw_UserTowerMapping> getProjectListUserWise(String userid, String finalRegion); 
}