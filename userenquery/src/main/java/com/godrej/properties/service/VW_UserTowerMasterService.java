package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.Vw_UserTowerMapping;

public interface VW_UserTowerMasterService {
	 List<Vw_UserTowerMapping> getProjectListUserWise(String userid); 
}
