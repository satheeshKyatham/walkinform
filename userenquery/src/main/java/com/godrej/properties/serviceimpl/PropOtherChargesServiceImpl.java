package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.PropOtherChargesDao;
import com.godrej.properties.model.PropOtherCharges;
import com.godrej.properties.service.PropOtherChargesService;


@Service("propOtherChargesService")
@Transactional
public class PropOtherChargesServiceImpl implements PropOtherChargesService{
	@Autowired
	PropOtherChargesDao propOtherChargesDao;
	
	@Override
	public List<PropOtherCharges> getCharges(String propSfid, String projectId) {
		// TODO Auto-generated method stub
		return propOtherChargesDao.getCharges(propSfid,projectId);
	}

	@Override
	public String getPropertyTypeStatus(String propSfid) {
		// TODO Auto-generated method stub
		return propOtherChargesDao.getPropertyTypeStatus(propSfid);
	}
	/*Changes By Satheesh for Inventory Update*/
	@Override
	public String updatePropertyStatus(String propSfid) {
		// TODO Auto-generated method stub
		return propOtherChargesDao.updatePropertyStatus(propSfid);
	}
}
