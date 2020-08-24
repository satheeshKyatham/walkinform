package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dto.VW_UserTowerMasterDao;
import com.godrej.properties.model.Vw_UserTowerMapping;
import com.godrej.properties.service.VW_UserTowerMasterService;

@Service("vW_UserTowerMasterService")
@Transactional
public class VW_UserTowerMasterServiceImpl implements VW_UserTowerMasterService {

	@Autowired
	 VW_UserTowerMasterDao vw_UserTowerMasterDao;

	@Override
	public List<Vw_UserTowerMapping> getProjectListUserWise(String userid) {
		return vw_UserTowerMasterDao.getProjectListUserWise(userid);
	}
}